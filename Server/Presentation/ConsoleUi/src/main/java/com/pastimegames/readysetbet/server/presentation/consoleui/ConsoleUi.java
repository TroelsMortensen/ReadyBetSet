package com.pastimegames.readysetbet.server.presentation.consoleui;

import com.pastimegames.readysetbet.core.application.gamemanager.GameManager;
import com.pastimegames.readysetbet.core.domain.eventpublisher.DomainEventListener;
import com.pastimegames.readysetbet.core.domain.eventpublisher.DomainEventPublisher;
import com.pastimegames.readysetbet.core.domain.events.HorseMoved;
import com.pastimegames.readysetbet.core.domain.events.RaceFinished;
import com.pastimegames.readysetbet.core.domain.entities.lobby.RaceOptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConsoleUi {
//    private final GameManager gameManager;
//    private List<HorseDto> horses = new ArrayList<>();
//
//    public ConsoleUi(GameManager diceRoller) {
//        this.gameManager = diceRoller;
//    }
//
//    public void start() {
//        setupListeners();
//
//        setupHorses();
//
//        RaceOptions options = new RaceOptions()
//                .setMoveTickTimeInMs(500);
//
//        gameManager.prepareForRacing(options);
//        gameManager.startRace();
//    }
//
//    private void setupHorses() {
//        HorseDto[] horseArray = {
//                new HorseDto("2/3", "blue"),
//                new HorseDto("4", "black"),
//                new HorseDto("5", "orange"),
//                new HorseDto("6", "red"),
//                new HorseDto("7", "black"),
//                new HorseDto("8", "red"),
//                new HorseDto("9", "orange"),
//                new HorseDto("10", "blue"),
//                new HorseDto("11/12", "blue")
//        };
//        horses.addAll(Arrays.asList(horseArray));
//    }
//
//    private void setupListeners() {
//        DomainEventPublisher.instance().subscribe(RaceFinished.type(), (DomainEventListener<RaceFinished>) raceFinished ->
//        {
//            System.out.println("Race finished, "+ raceFinished.winnerHorseName() + " won the race");
//        });
//
//        DomainEventPublisher.instance().subscribe(HorseMoved.type(), (DomainEventListener<HorseMoved>) horseMoved -> {
//            HorseDto horseBeingMoved = horses.stream()
//                    .filter(horseDto -> horseDto.name()
//                            .equals(horseMoved.horseName()))
//                    .findFirst()
//                    .get();
//            horseBeingMoved.updatePosition(horseMoved.currentPosition());
//            printBoard();
//        });
//
//    }
//
//    private void printBoard() {
//        StringBuilder sb = new StringBuilder();
//        sb.append(topRow()).append("\n");
//        for (HorseDto horse : horses) {
//            sb.append(createHorseRow(horse));
//            sb.append("\n");
//        }
//        sb.append("\n");
//        sb.append("\n");
//        sb.append("\n");
//        sb.append("\n");
//        System.out.println(sb);
//    }
//
//    private String createHorseRow(HorseDto horse) {
//
//        String row = "";
//        for (int i = 0; i < horse.position(); i++) {
//            row += "\t\t";
//        }
//        row += horse.name();
//        return row;
//    }
//
//    private String topRow() {
//        String s = "0\t\t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10\t\t11\t\t12\t\t13\t\t14\t\t15";
//        return s;
//    }
}
