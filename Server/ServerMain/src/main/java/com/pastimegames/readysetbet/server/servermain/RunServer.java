package com.pastimegames.readysetbet.server.servermain;

import com.pastimegames.readysetbet.core.application.gamemanager.GameManager;
import com.pastimegames.readysetbet.core.application.gamemanager.GameManagerImpl;
import com.pastimegames.readysetbet.core.domain.domainservices.DiceRoller;
import com.pastimegames.readysetbet.server.infrastructure.javarandomdicerolling.JavaRandomDiceRoller;
import com.pastimegames.readysetbet.server.socketserver.SocketServer;

public class RunServer {

    public static void main(String[] args) {
        DiceRoller diceRoller = new JavaRandomDiceRoller();
        GameManager gameManager = new GameManagerImpl(diceRoller);
        SocketServer socketServer = new SocketServer(gameManager);
        new Thread(socketServer::runServer).start();
    }
}
