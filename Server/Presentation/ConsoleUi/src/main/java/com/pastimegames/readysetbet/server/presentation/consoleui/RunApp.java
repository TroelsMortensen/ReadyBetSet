package com.pastimegames.readysetbet.server.presentation.consoleui;

import com.pastimegames.readysetbet.core.domain.domainservices.DiceRoller;
import com.pastimegames.readysetbet.infrastructure.dicerolling.JavaRandomDiceRoller;

public class RunApp {

    public static void main(String[] args) {
        DiceRoller diceRoller = new JavaRandomDiceRoller();
        new ConsoleUi(diceRoller).start();
    }

}
