package com.pastimegames.readysetbet.core.domain.entities.betting.propcards;

import com.pastimegames.readysetbet.core.domain.entities.race.HorseColor;
import com.pastimegames.readysetbet.core.domain.entities.race.RaceResult;

import java.util.Arrays;
import java.util.List;

public class PropCardDeck {

    public static List<PropCard> cards = Arrays.asList(

            /* PurpleAheadOfAllBlues */
            new PropCard("PurpleAheadOfAllBlues", 3, -3, raceResult
                    ->
            {
                RaceResult.HorseData purpleHorse = raceResult.horses().get("7");

                List<RaceResult.HorseData> blueHorses = raceResult.horses().values().stream()
                        .filter(horse -> horse.color() == HorseColor.BLUE)
                        .toList();

                for (RaceResult.HorseData blueHorse : blueHorses) {
                    if (blueHorse.finalPosition() >= purpleHorse.finalPosition()) return false;
                }

                return true;
            }),

            /* RedsAheadOfAllBlues */
            new PropCard("RedsAheadOfAllBlues", 5, -2, raceResult
                    ->
            {
                List<RaceResult.HorseData> redHorses = raceResult.horses().values().stream()
                        .filter(horse -> horse.color() == HorseColor.RED)
                        .toList();

                List<RaceResult.HorseData> blueHorses = raceResult.horses().values().stream()
                        .filter(horse -> horse.color() == HorseColor.BLUE)
                        .toList();

                for (RaceResult.HorseData blueHorse : blueHorses) {
                    if (redHorses.stream()
                            .anyMatch(redHorse -> blueHorse.finalPosition() >= redHorse.finalPosition())
                    ) {
                        return false;
                    }
                }

                return true;
            }));
}
