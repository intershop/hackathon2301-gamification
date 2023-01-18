package com.intershop.hackathon.gamification.orm;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;

import java.util.Objects;
import java.util.Optional;

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
}
