package com.intershop.hackathon.gamification.orm;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

public class AchievementRepository implements PanacheRepositoryBase<Achievement, String>
{
	public Achievement create(Achievement achievement)
	{
		Objects.requireNonNull(achievement);
		achievement.persist();
		return achievement;
	}
}
