package com.intershop.hackathon.gamification.orm;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import java.util.Set;
import java.util.TreeSet;

import com.intershop.hackathon.gamification.orm.Achievement;

@Entity
@Table(name = "questUser")
public class User extends PanacheEntity
{
	public String username;
	public String display_name;
	public String email;

	public int experience_points;
	@Transient
	public int level;

	public String achievement_title;

	@ManyToMany
	public Set<Achievement> achievements = new TreeSet<>();

	public void addAchievement(String achievement)
	{
		this.achievements.add(new Achievement());
	}
}
