package com.pastimegames.readysetbet.core.domain.entities.race;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

class RaceResultTest {

    @Test
    public void testWin(){
        RaceResult rr1 = createRaceResult(new int[]{
                1, 4, 7, 0, 15, 14, 14, 13, 12
        });
        assertTrue( rr1.isWIN("7"));

        RaceResult rr2 = createRaceResult(new int[]{
                14, 13, 12, 6, 10, 7, 0, 15, 14,
        });
        assertTrue( rr2.isWIN("10"));

    }

    @Test
    public void testPlace(){
        RaceResult rr1 = createRaceResult(new int[]{
                1, 4, 7, 0, 15, 14, 14, 13, 12
        });
        assertTrue( rr1.isPLACE("8"));
        assertTrue( rr1.isPLACE("9"));
        assertTrue( rr1.isPLACE("7"));

        RaceResult rr2 = createRaceResult(new int[]{
                14, 13, 12, 6, 10, 7, 0, 15, 14,
        });
        assertTrue( rr2.isPLACE("2/3"));
        assertTrue( rr2.isPLACE("11/12"));
        assertTrue( rr2.isPLACE("10"));
    }

    @Test
    public void testShow(){
        RaceResult rr1 = createRaceResult(new int[]{
                1, 4, 7, 0, 15, 14, 14, 13, 12
        });
        assertTrue(rr1.isSHOW("7"));
        assertTrue(rr1.isSHOW("8"));
        assertTrue(rr1.isSHOW("9"));
        assertTrue(rr1.isSHOW("10"));

        RaceResult rr2 = createRaceResult(new int[]{
                0, 1, 10, 10, 10, 7, 0, 15, 14
        });
        assertTrue(rr2.isSHOW("10"));
        assertTrue(rr2.isSHOW("11/12"));
        assertTrue(rr2.isSHOW("5"));
        assertTrue(rr2.isSHOW("6"));
        assertTrue(rr2.isSHOW("7"));

        RaceResult rr3 = createRaceResult(new int[]{
                14, 13, 12, 6, 10, 7, 0, 15, 14,
        });RaceResult rr4 = createRaceResult(new int[]{
                9, 13, 12, 6, 10, 7, 0, 15, 11,
        });
    }

    private RaceResult createRaceResult(int[] places){
        Map<String, RaceResult.HorseData> map = new HashMap<>();
        map.put("2/3", new RaceResult.HorseData("2/3", HorseColor.BLUE, places[0]));
        map.put("4", new RaceResult.HorseData("4", HorseColor.BLUE, places[1]));
        map.put("5", new RaceResult.HorseData("5", HorseColor.BLUE, places[2]));
        map.put("6", new RaceResult.HorseData("6", HorseColor.BLUE, places[3]));
        map.put("7", new RaceResult.HorseData("7", HorseColor.BLUE, places[4]));
        map.put("8", new RaceResult.HorseData("8", HorseColor.BLUE, places[5]));
        map.put("9", new RaceResult.HorseData("9", HorseColor.BLUE, places[6]));
        map.put("10", new RaceResult.HorseData("10", HorseColor.BLUE, places[7]));
        map.put("11/12", new RaceResult.HorseData("11/12", HorseColor.BLUE, places[8]));

        return new RaceResult(map);
    }

}