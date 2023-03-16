package com.pastimegames.readysetbet.server.servermain;

import com.pastimegames.readysetbet.core.application.gamemanager.GameManager;
import com.pastimegames.readysetbet.core.application.gamemanager.GameManagerImpl;
import com.pastimegames.readysetbet.core.domain.domainservices.DiceRoller;
import com.pastimegames.readysetbet.server.infrastructure.javarandomdicerolling.JavaRandomDiceRoller;
import com.pastimegames.readysetbet.server.presentation.javafxgui.Core.ViewHandler;
import com.pastimegames.readysetbet.server.presentation.javafxgui.Core.ViewModelFactory;
import com.pastimegames.readysetbet.server.presentation.javafxgui.Model.Model;
import com.pastimegames.readysetbet.server.socketserver.SocketServer;
import javafx.application.Application;
import javafx.stage.Stage;

public class RunServer extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        DiceRoller diceRoller = new JavaRandomDiceRoller();
        GameManager gameManager = new GameManagerImpl(diceRoller);
        SocketServer socketServer = new SocketServer(gameManager);
        new Thread(socketServer::runServer).start();

        Model model = new Model();
        ViewModelFactory viewModelFactory = new ViewModelFactory(model);
        ViewHandler viewHandler = new ViewHandler(viewModelFactory);
        viewHandler.start(primaryStage);
    }
}
