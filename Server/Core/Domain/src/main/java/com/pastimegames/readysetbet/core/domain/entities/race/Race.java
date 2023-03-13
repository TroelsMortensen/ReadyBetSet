package com.pastimegames.readysetbet.core.domain.entities.race;

import com.pastimegames.readysetbet.core.domain.eventpublisher.DomainEventPublisher;
import com.pastimegames.readysetbet.core.domain.domainservices.DiceRoller;
import com.pastimegames.readysetbet.core.domain.events.*;
import com.pastimegames.readysetbet.core.domain.valueobjects.DiceRoll;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Race {
    private final List<Horse> horses = new ArrayList<>();
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

        DiceRoll diceRoll = diceRoller.rollDice();

        DomainEventPublisher.instance().publish(new DiceRolled(diceRoll.firstDice(), diceRoll.secondDice()));

        Horse horseToMove = findHorse(diceRoll.sumAsHorseId());

        moveHorse(horseToMove);
        checkForWinner(horseToMove);
    }

    private void moveHorse(Horse horseToMove) {
        int prevPosition = horseToMove.position();

        executeMove(horseToMove);
        int newPosition = horseToMove.position();

        DomainEventPublisher.instance().publish(new HorseMoved(horseToMove.name(), horseToMove.position()));
        updateNumberOfHorsesAcrossBetLine(prevPosition, newPosition, horseToMove);
    }

    private void updateNumberOfHorsesAcrossBetLine(int prevPosition, int newPosition, Horse horse) {
        if (prevPosition < BET_LINE && newPosition > BET_LINE) {
            numberOfHorsesAcrossBettingLine++;

            DomainEventPublisher.instance().publish(new HorseCrossedBetLine(horse.name()));
        }
        if (numberOfHorsesAcrossBettingLine == 3) {
            DomainEventPublisher.instance().publish(new BetsAreClosed());
        }
    }

    private void executeMove(Horse horseBeingMoved) {
        if (horseBeingMoved.equals(previouslyMovedHorse)) {
            horseBeingMoved.sprint();
            previouslyMovedHorse = null;
        } else {
            horseBeingMoved.move();
            previouslyMovedHorse = horseBeingMoved;
        }
    }

    private void checkForWinner(Horse potentialWinnerHorse) {
        if (potentialWinnerHorse.position() == FINISH_LINE_INDEX) {
            DomainEventPublisher.instance().publish(new RaceFinished(potentialWinnerHorse.name()));
        }
    }

    private Horse findHorse(String horseName) {
        Horse horseToMove = horses
                .stream()
                .filter(horse -> horse.name().equals(horseName))
                .findFirst()
                .get();
        return horseToMove;
    }
}
