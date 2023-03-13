package com.pastimegames.readysetbet.server.presentation.consoleui;

import com.pastimegames.readysetbet.core.domain.eventpublisher.DomainEventListener;
import com.pastimegames.readysetbet.core.domain.eventpublisher.DomainEventPublisher;
import com.pastimegames.readysetbet.core.domain.domainservices.DiceRoller;
import com.pastimegames.readysetbet.core.domain.entities.Horse;
import com.pastimegames.readysetbet.core.domain.entities.Race;
import com.pastimegames.readysetbet.core.domain.events.HorseMoved;
import com.pastimegames.readysetbet.core.domain.events.RaceFinished;
import com.pastimegames.readysetbet.infrastructure.dicerolling.JavaRandomDiceRoller;
import com.pastimegames.readysetbet.shared.viewmodels.HorseVM;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConsoleUi {
    private final DiceRoller diceRoller;
    private boolean raceIsWon = false;
    private List<HorseVM> horses = new ArrayList<>();

    public ConsoleUi(DiceRoller diceRoller) {
        this.diceRoller = diceRoller;
    }

    public void start() {
        setupListeners();

        setupHorses();

        Race race = new Race();
        race.startRace();

        while (!raceIsWon) {
            race.moveHorse(diceRoller);
            printBoard();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException ignored) {}
        }
    }

    private void setupHorses() {
        HorseVM[] horseArray = {
                new HorseVM("2/3", "blue"),
                new HorseVM("4", "black"),
                new HorseVM("5", "orange"),
                new HorseVM("6", "red"),
                new HorseVM("7", "black"),
                new HorseVM("8", "red"),
                new HorseVM("9", "orange"),
                new HorseVM("10", "blue"),
                new HorseVM("11/12", "blue")
        };
        horses.addAll(Arrays.asList(horseArray));
    }

    private void setupListeners() {
        DomainEventPublisher.instance().subscribe(RaceFinished.type(), (DomainEventListener<RaceFinished>) raceFinished ->
        {
            System.out.println("Race finished, "+ raceFinished.winnerHorseName() + " won the race");
        });

        DomainEventPublisher.instance().subscribe(HorseMoved.type(), (DomainEventListener<HorseMoved>) horseMoved -> {
            HorseVM horseBeingMoved = horses.stream()
                    .filter(horseVM -> horseVM.name()
                            .equals(horseMoved.horseName()))
                    .findFirst()
                    .get();
            horseBeingMoved.updatePosition(horseMoved.position());
        });

        DomainEventPublisher.instance().subscribe(RaceFinished.type(), (DomainEventListener<RaceFinished>) raceFinished -> {
            raceIsWon = true;
        });
    }

    private void printBoard() {
        StringBuilder sb = new StringBuilder();
        sb.append(topRow()).append("\n");
        for (HorseVM horse : horses) {
            sb.append(createHorseRow(horse));
            sb.append("\n");
        }
        sb.append("\n");
        sb.append("\n");
        sb.append("\n");
        sb.append("\n");
        System.out.println(sb);
    }

    private String createHorseRow(HorseVM horse) {

        String row = "";
        for (int i = 0; i < horse.position(); i++) {
            row += "\t\t";
        }
        row += horse.name();
        return row;
    }

    private String topRow() {
        String s = "0\t\t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10\t\t11\t\t12\t\t13\t\t14\t\t15";
        return s;
    }
}
