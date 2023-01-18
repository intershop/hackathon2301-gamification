package com.intershop.hackathon.gamification;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.Entity;
import javax.persistence.Id;
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
