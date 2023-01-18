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
	@Inject LevelCalculator levelCalculator;

	public User create(User user)
	{
		Objects.requireNonNull(user);
		user.persist();
		return user;
	}


	public Optional<User> findByUsername(String username)
	{
		Optional<User> userOptional = find("username", username).firstResultOptional();
		return updateLevel(userOptional);
	}

	public Optional<User> findByEmail(String email)
	{
		Optional<User> userOptional = find("email", email.toLowerCase()).firstResultOptional();
		return updateLevel(userOptional);
	}

	private Optional<User> updateLevel(Optional<User> userOptional)
	{
		if (userOptional.isPresent())
		{
			User user = userOptional.get();
			user.level = levelCalculator.getLevel(user.experience_points);
			return Optional.of(user);
		}
		return userOptional;
	}
}
