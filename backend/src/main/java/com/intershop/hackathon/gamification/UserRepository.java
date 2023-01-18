package com.intershop.hackathon.gamification;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class UserRepository
{


	List<User> users = dummyData();

	public List<User> findAll()
	{
		return users;
	}

	public User findById(String username)
	{
		User result = users.stream()
				.filter(u -> u.getUsername().equals(username))
				.findFirst()
				.get();

		return result;
	}

	public User insert(User user)
	{
		users.add(user);
		return findById(user.getUsername());
	}

	private List<User> dummyData()
	{
		User paul = new User("paul_anton_rainer");
		paul.setEmail("paul.anton.rainer@intershop.com");
		paul.setDisplay_name("Paul Anton Rainer");
		paul.addAchievement("Debugger");
		paul.addAchievement("Self Sustained");
		paul.addAchievement("Peace Was No Decision");
		paul.addAchievement("Lucky Luke");
		paul.addAchievement("No Hurry");
		paul.addAchievement("Firefighter");
		paul.addAchievement("Street");

		User steven = new User("steven_heyder");
		steven.setEmail("steven.heyder@intershop.com");
		steven.setDisplay_name("Steven Heyder");
		steven.addAchievement("Debugger");
		steven.addAchievement("Self Sustained");
		steven.addAchievement("Peace Was No Decision");
		steven.addAchievement("Lucky Luke");
		steven.addAchievement("No Hurry");
		steven.addAchievement("Firefighter");
		steven.addAchievement("Street");

		User sebastian = new User("sebastian_glass");
		sebastian.setEmail("sebastian.glass@intershop.com");
		sebastian.setDisplay_name("Sebastian Glass");
		sebastian.addAchievement("Debugger");
		sebastian.addAchievement("Self Sustained");
		sebastian.addAchievement("Peace Was No Decision");
		sebastian.addAchievement("Lucky Luke");
		sebastian.addAchievement("No Hurry");
		sebastian.addAchievement("Firefighter");
		sebastian.addAchievement("Street");

		User sven = new User("sven_dibowski");
		sven.setEmail("sven.dibowski@intershop.com");
		sven.setDisplay_name("Sven Dibowski");
		sven.addAchievement("Debugger");
		sven.addAchievement("Self Sustained");
		sven.addAchievement("Peace Was No Decision");
		sven.addAchievement("Lucky Luke");
		sven.addAchievement("No Hurry");
		sven.addAchievement("Firefighter");
		sven.addAchievement("Street");

		User ronny = new User("ronny_wilms");
		ronny.setEmail("ronny.wilms@intershop.com");
		ronny.setDisplay_name("Ronny Wilms");
		ronny.addAchievement("Debugger");
		ronny.addAchievement("Self Sustained");
		ronny.addAchievement("Peace Was No Decision");
		ronny.addAchievement("Lucky Luke");
		ronny.addAchievement("No Hurry");
		ronny.addAchievement("Firefighter");
		ronny.addAchievement("Street");

		User can = new User("can_arici");
		can.setEmail("can.arici@experts.com");
		can.setDisplay_name("Can Arici");
		can.addAchievement("Debugger");
		can.addAchievement("Self Sustained");
		can.addAchievement("Peace Was No Decision");
		can.addAchievement("Lucky Luke");
		can.addAchievement("No Hurry");
		can.addAchievement("Firefighter");
		can.addAchievement("Street");

		return new ArrayList<>()
		{{
			add(paul);
			add(steven);
			add(sebastian);
			add(sven);
			add(ronny);
			add(can);
		}};
	}
}
