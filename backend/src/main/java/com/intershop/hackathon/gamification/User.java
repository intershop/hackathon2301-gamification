package com.intershop.hackathon.gamification;

import java.util.ArrayList;
import java.util.List;

public class User
{
	private final String username;
	private String display_name;

	public String getDisplay_name()
	{
		return display_name;
	}

	public void setDisplay_name(String display_name)
	{
		this.display_name = display_name;
	}

	private String email;
	private String achievement_title;
	private List<String> achievements;



	public User(String username)
	{

		this.username = username;
		this.achievements = new ArrayList<>();
	}

	public String getUsername()
	{
		return username;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getAchievement_title()
	{
		return achievement_title;
	}

	public void setAchievement_title(String achievement_title)
	{
		this.achievement_title = achievement_title;
	}

	public List<String> getAchievements()
	{
		return achievements;
	}

	public void addAchievement(String achievement){
		this.achievements.add(achievement);
	}
}
