package com.pastimegames.readysetbet.core.domain.entities.race;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class RaceResultTest {

    @Test
    public void testWin(){

    }

    @Test
    public void testPlace(){

    }

    @Test
    public void testShow(){

    }

    private RaceResult createRaceResult(int[] places){
        Map<String, RaceResult.HorseData> map = new HashMap<>();
        map.put("2/3", new RaceResult.HorseData("2/3", HorseColor.BLUE, places[0]));
        map.put("4", new RaceResult.HorseData("3", HorseColor.BLUE, places[1]));
        map.put("5", new RaceResult.HorseData("5", HorseColor.BLUE, places[2]));
        map.put("6", new RaceResult.HorseData("6", HorseColor.BLUE, places[3]));
        map.put("7", new RaceResult.HorseData("7", HorseColor.BLUE, places[4]));
        map.put("8", new RaceResult.HorseData("8", HorseColor.BLUE, places[5]));
        map.put("9", new RaceResult.HorseData("9", HorseColor.BLUE, places[6]));
        map.put("10", new RaceResult.HorseData("10", HorseColor.BLUE, places[7]));
        map.put("11", new RaceResult.HorseData("11", HorseColor.BLUE, places[8]));

        return new RaceResult(map);
    }

}