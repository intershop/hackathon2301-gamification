package com.intershop.hackathon.gamification;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class LevelCalculatorTest {

    @Test
    public void levelTest(){
        LevelCalculator calc = new LevelCalculator();
        System.out.println(Arrays.toString(calc.getLevels()));
    }
    @Test
    public void testGetLevel(){
        LevelCalculator calc = new LevelCalculator();
        assert calc.getLevel(9) == 0;
        assert calc.getLevel(51) == 4;
        assert calc.getLevel(1000) == 20;
    }
    @Test
    public void testExpPoints(){
        System.out.println(LevelCalculator.getExpPoints(1));
        System.out.println(LevelCalculator.getExpPoints(3));
        System.out.println(((double)LevelCalculator.getExpPoints(10000))/10000);
        System.out.println(((double)LevelCalculator.getExpPoints(10000))/10000);
        System.out.println(((double)LevelCalculator.getExpPoints(10000))/10000);
    }

    @Test
    public void testMapDifficultyLevel() // TODO should be a ParameterizedTest
    {
        LevelCalculator calc = new LevelCalculator();
        Assertions.assertEquals(1, calc.mapDifficultyLevel("Minor"));
        Assertions.assertEquals(2, calc.mapDifficultyLevel("Major"));
        Assertions.assertEquals(3, calc.mapDifficultyLevel("Critical"));
        Assertions.assertEquals(5, calc.mapDifficultyLevel("Blocker"));
        Assertions.assertEquals(0, calc.mapDifficultyLevel("invalid"));
    }
}
