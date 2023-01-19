package com.intershop.hackathon.gamification.orm;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import java.util.Objects;
import java.util.Set;

@Entity
public class Achievement extends PanacheEntityBase
{
	@Id
	@GeneratedValue
	public String id;
	@Column(nullable = false, updatable = false)
	public String title;
	@Column
	public String description;
	@Column
	public int tier; // TODO should that be part of the id?

	@ManyToMany(mappedBy = "achievements")
	private Set<User> users;

	@Override public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if (!(obj instanceof User))
		{
			return false;
		}
		return this.id != null && this.id.equals(((User)obj).id) && this.tier == (((Achievement)obj).tier);
	}

	@Override public int hashCode()
	{
		return Objects.hashCode(title + tier);
	}
}
