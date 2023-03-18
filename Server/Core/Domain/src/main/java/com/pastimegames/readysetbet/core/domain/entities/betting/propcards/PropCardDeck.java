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
                RaceResult.Horse purpleHorse = raceResult.horses().stream()
                        .filter(horseResult -> horseResult.color() == HorseColor.PURPLE)
                        .findFirst()
                        .get();

                List<RaceResult.Horse> blueHorses = raceResult.horses().stream()
                        .filter(horse -> horse.color() == HorseColor.BLUE)
                        .toList();

                for (RaceResult.Horse blueHorse : blueHorses) {
                    if (blueHorse.finalPosition() >= purpleHorse.finalPosition()) return false;
                }

                return true;
            }),

            /* RedsAheadOfAllBlues */
            new PropCard("RedsAheadOfAllBlues", 5, -2, raceResult
                    ->
            {
                List<RaceResult.Horse> redHorses = raceResult.horses().stream()
                        .filter(horse -> horse.color() == HorseColor.RED)
                        .toList();

                List<RaceResult.Horse> blueHorses = raceResult.horses().stream()
                        .filter(horse -> horse.color() == HorseColor.BLUE)
                        .toList();

                for (RaceResult.Horse blueHorse : blueHorses) {
                    if (redHorses.stream()
                            .anyMatch(redHorse -> blueHorse.finalPosition() >= redHorse.finalPosition())
                    ) {
                        return false;
                    }
                }

                return true;
            }));
}
