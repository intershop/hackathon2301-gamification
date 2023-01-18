package com.intershop.hackathon.gamification;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;


public class AchievementRepository
{
	public List<Achievement> getAchievementDummy(){
		Achievement debugger = new Achievement();
		debugger.achievementId = "1";
		debugger.title = "Debugger";
		debugger.description = "Solve x Bugs";

//		Achievement selfSustained = new Achievement();
//		debugger.id = "2";
//		debugger.title = "Self Sustained";
//		debugger.description = "Solve x Bugs";
//
//		Achievement peaceWasNoDecision = new Achievement();
//		debugger.id = "3";
//		debugger.title = "Peace Was No Decision";
//		debugger.description = "Solve x Bugs";
//
//		Achievement luckyLuke = new Achievement();
//		debugger.id = "4";
//		debugger.title = "Lucky Luke";
//		debugger.description = "Solve x Bugs";
//
//		Achievement noHurry = new Achievement();
//		debugger.id = "5";
//		debugger.title = "No Hurry";
//		debugger.description = "Solve x Bugs";
//
//		Achievement fireFighter = new Achievement();
//		debugger.id = "6";
//		debugger.title = "Firefighter";
//		debugger.description = "Solve x Bugs";
//
//		Achievement street = new Achievement();
//		debugger.id = "7";
//		debugger.title = "Street";
//		debugger.description = "Solve x Bugs";

		List<Achievement> data = new ArrayList<>();
		data.add(debugger);
//		data.add( selfSustained);
//		data.add( peaceWasNoDecision);
//		data.add( luckyLuke);
//		data.add( noHurry);
//		data.add( fireFighter);
//		data.add( street);

		return data;
	}
}
