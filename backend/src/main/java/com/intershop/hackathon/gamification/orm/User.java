package com.intershop.hackathon.gamification.orm;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

import org.hibernate.annotations.NaturalId;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.intershop.hackathon.gamification.LevelCalculator;

@Entity
@Table(name = "questUser")
public class User extends PanacheEntityBase
{
	@Id
	@GeneratedValue
	public Long id;
	@NaturalId
	@Column(updatable = false, nullable = false)
	public String username;
	@Column
	public String display_name;
	@Column(nullable = false)
	public String email;
	@Column(nullable = true)
	public int experience_points;

	@ManyToMany(cascade = {
		CascadeType.PERSIST,
		CascadeType.MERGE
	},
	fetch = FetchType.EAGER)
	@JoinTable(name = "user_achievements",
		joinColumns = @JoinColumn (name = "user_id"),
		inverseJoinColumns = @JoinColumn(name = "achievement_id")
	)
	public Set<Achievement> achievements = new TreeSet<>();
    @Transient
	private LevelCalculator levelCalculator = new LevelCalculator();

	public void addAchievement(Achievement achievement)
	{
		this.achievements.add(new Achievement());
	}

	public void removeAchievement(Achievement achievement)
	{
		this.achievements.remove(new Achievement());
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if (!(obj instanceof User))
		{
			return false;
		}
		return this.id != null && this.id.equals(((User)obj).id);
	}

	@Override public int hashCode()
	{
		return Objects.hashCode(email);
	}

	public int getLevel()
	{
		return levelCalculator.getLevel(this.experience_points) +1;
	}

	@JsonProperty("experience_points_current_level")
	public int getExperiencePointsCurrentLevel()
	{
		int level = getLevel()-1;

		if (level == 0)
		{
			return 0;
		}

		return levelCalculator.getExperiencePoints(level -1);
	}

	@JsonProperty("experience_points_next_level")
	public int getExperiencePointsNextLevel()
	{
		return levelCalculator.getExperiencePoints(getLevel()-1);
	}
}
