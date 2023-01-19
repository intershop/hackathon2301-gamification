package com.intershop.hackathon.gamification;

import javax.enterprise.context.ApplicationScoped;


public class LevelCalculator {
    private static int MAX_LEVEL = 20;
    private static double LEVEL_STEP = 1.15;

    private int[] levelCaps = null;

    public LevelCalculator(){
         getLevels();
    }

    public int[] getLevels()
    {
        if(levelCaps!=null)
        {
            return levelCaps;
        }

        levelCaps= new int[MAX_LEVEL];
        levelCaps[0] = 10;
        for(int i = 1 ; i < MAX_LEVEL; i++)
        {
            levelCaps[i] = (int) (Math.round((Math.pow(LEVEL_STEP,i))) + levelCaps[i-1] + 10);
        }

        return levelCaps;
    }

    public int getLevel(int expPoints)
    {
        for(int level=0;level < MAX_LEVEL;level++ )
        {
            if (expPoints < levelCaps[level])
            {
                return level;
            }
        }
        return MAX_LEVEL;
    }

    public int mapDifficultyLevel(String severity)
    {
        switch(severity)
        {
            case "Minor": return 1;
            case "Major": return 2;
            case "Critical": return 3;
            case "Blocker": return 5;
        }
        return 0;
    }

    public static int getExpPoints(int difLevel)
    {
        int points= 0;
        for (int expThrow=0;expThrow<difLevel; expThrow++ )
        {
            points += Math.ceil(Math.random()*3);

        }
        return points;
    }
}
