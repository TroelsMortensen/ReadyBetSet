package com.pastimegames.readysetbet.javafxclient.socketclient;

import com.pastimegames.readysetbet.javafxclient.socketclient.Core.ViewHandler;
import com.pastimegames.readysetbet.javafxclient.socketclient.Core.ViewModelFactory;
import com.pastimegames.readysetbet.javafxclient.socketclient.Model.Model;
import com.pastimegames.readysetbet.javafxclient.socketclient.Networking.NetworkModel;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class RunClient extends Application {
    public static void main(String[] args) {
        launch();
    }

    public void start(Stage primaryStage) throws IOException {

        Model model = new NetworkModel();
        ViewModelFactory viewModelFactory = new ViewModelFactory(model);
        ViewHandler viewHandler = new ViewHandler(viewModelFactory);
        viewHandler.start(primaryStage);
    }
}
