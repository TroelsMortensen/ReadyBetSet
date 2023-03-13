package com.pastimegames.readysetbet.core.domain.entities.race;

import com.pastimegames.readysetbet.core.domain.eventpublisher.DomainEventPublisher;
import com.pastimegames.readysetbet.core.domain.domainservices.DiceRoller;
import com.pastimegames.readysetbet.core.domain.events.*;
import com.pastimegames.readysetbet.core.domain.valueobjects.DiceRoll;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Race {
    private List<Horse> horses = new ArrayList<>();
    private Horse previouslyMovedHorse = null;
    private int numberOfHorsesAcrossBettingLine = 0;
    private static final float BET_LINE = 9.5f;
    private static final int FINISH_LINE_INDEX = 15;


    public Race() {

    }

    public void initializeRace() {
        createHorses();
        DomainEventPublisher.instance().publish(new RaceInitialized());
    }

    private void createHorses() {
        Horse[] horseArray = {
                new Horse("2/3", HorseColors.BLUE, 3),
                new Horse("4", HorseColors.BLUE, 3),
                new Horse("5", HorseColors.ORANGE, 2),
                new Horse("6", HorseColors.RED, 1),
                new Horse("7", HorseColors.BLACK, 0),
                new Horse("8", HorseColors.RED, 1),
                new Horse("9", HorseColors.ORANGE, 2),
                new Horse("10", HorseColors.BLUE, 3),
                new Horse("11/12", HorseColors.BLUE, 3)
        };
        horses.addAll(Arrays.asList(horseArray));
    }

    public void moveHorse(DiceRoller diceRoller) {
        System.out.println("Moving horse");
        DiceRoll diceRoll = rolleDice(diceRoller);
        System.out.println("Rolled " + diceRoll.sumAsHorseId());
        Horse horseToMove = findHorse(diceRoll);

        moveHorse(horseToMove);
        checkForWinner(horseToMove);
    }

    private void updateNumberOfHorsesAcrossBetLine(int prevPosition, int newPosition, Horse horse) {
        if (prevPosition < BET_LINE && newPosition > BET_LINE) {
            numberOfHorsesAcrossBettingLine++;

            DomainEventPublisher.instance().publish(new HorseCrossedBetLine(horse.name()));
        }
        if (numberOfHorsesAcrossBettingLine == 3) {
            DomainEventPublisher.instance().publish(BetsAreClosed.class.getName());
        }
    }

    private void moveHorse(Horse horse) {
        int prevPosition = horse.position();

        executeMove(horse);
        int newPosition = horse.position();

        DomainEventPublisher.instance().publish(new HorseMoved(horse.name(), horse.position()));
        updateNumberOfHorsesAcrossBetLine(prevPosition, newPosition, horse);
    }

    private void executeMove(Horse horse) {
        if (horse.equals(previouslyMovedHorse)) {
            horse.sprint();
            previouslyMovedHorse = null;
        } else {
            horse.move();
            previouslyMovedHorse = horse;
        }
    }

    private void checkForWinner(Horse horse) {
        if (horse.position() == FINISH_LINE_INDEX) {
            DomainEventPublisher.instance().publish(new RaceFinished(horse.name()));
        }
    }

    private Horse findHorse(DiceRoll diceRoll) {
        Horse horseToMove = horses
                .stream()
                .filter(horse -> horse.name().equals(diceRoll.sumAsHorseId()))
                .findFirst()
                .get();
        return horseToMove;
    }

    private DiceRoll rolleDice(DiceRoller diceRoller) {
        return diceRoller.rollDice();
    }
}
