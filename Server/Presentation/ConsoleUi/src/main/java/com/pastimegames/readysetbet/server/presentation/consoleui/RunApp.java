package com.pastimegames.readysetbet.server.presentation.consoleui;

import com.pastimegames.readysetbet.core.application.gamemanager.GameManager;
import com.pastimegames.readysetbet.core.application.gamemanager.GameManagerImpl;
import com.pastimegames.readysetbet.core.domain.domainservices.DiceRoller;
import com.pastimegames.readysetbet.server.infrastructure.javarandomdicerolling.JavaRandomDiceRoller;

public class RunApp {

    public static void main(String[] args) {
        DiceRoller diceRoller = new JavaRandomDiceRoller();
        GameManager gm = new GameManagerImpl(diceRoller);
        new ConsoleUi(gm).start();
    }

}
