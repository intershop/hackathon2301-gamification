package com.intershop.hackathon.gamification.orm;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
public class Achievement extends PanacheEntity
{
	public String achievementId;
	public String title;
	public String description;
	public int tier;
	@ManyToMany
	Set<User> users;

	public static Achievement findById(String id){
		return find("id", id).firstResult();
	}
}
