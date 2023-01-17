package com.intershop.hackathon.gamification;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class UserRepository
{
	List<User> users = new ArrayList<>(){{
		add(new User("sven.dibowski@example.com"));
		add(new User("ronny.wilms@example.com"));
		add(new User("can.arici@example.com"));
	}};

	public List<User> findAll() {
		return users;
	}

	public User findByEmail(String email) {
		User result = users.stream()
				.filter(u -> u.getEmail().equals(email) )
				.findFirst()
				.get();

		return result;
	}

	public User insert (User user){
		users.add(user);
		return findByEmail(user.getEmail());
	}
}
