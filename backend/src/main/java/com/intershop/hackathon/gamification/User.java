package com.intershop.hackathon.gamification;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Table(name = "questUser")
public class User extends PanacheEntity
{
	public String username;
	public String display_name;
	public String email;
	public String achievement_title;

	@ManyToMany
	public Set<Achievement> achievements = new TreeSet<>();









	public void addAchievement(String achievement){
		this.achievements.add(new Achievement());
	}
}
