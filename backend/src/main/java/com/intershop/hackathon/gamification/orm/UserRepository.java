package com.intershop.hackathon.gamification.orm;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import java.util.Objects;
import java.util.Optional;

import com.intershop.hackathon.gamification.LevelCalculator;

@ApplicationScoped
public class UserRepository implements PanacheRepositoryBase<User, String>
{
	public User create(User user)
	{
		Objects.requireNonNull(user);
		user.persist();
		return user;
	}

	public Optional<User> findByUsername(String username)
	{
		return find("username", username).firstResultOptional();
	}

	public Optional<User> findByEmail(String email)
	{
		return find("email", email.toLowerCase()).firstResultOptional();
	}

}
