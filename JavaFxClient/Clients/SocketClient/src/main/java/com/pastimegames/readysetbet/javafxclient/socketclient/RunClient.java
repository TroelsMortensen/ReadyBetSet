package com.pastimegames.readysetbet.javafxclient.socketclient;

import com.pastimegames.readysetbet.javafxclient.socketclient.Core.ClientFactory;
import com.pastimegames.readysetbet.javafxclient.socketclient.Core.ViewHandler;
import com.pastimegames.readysetbet.javafxclient.socketclient.Core.ViewModelFactory;
import com.pastimegames.readysetbet.javafxclient.socketclient.Model.Model;
import com.pastimegames.readysetbet.javafxclient.socketclient.Model.ModelManager;
import javafx.application.Application;
import javafx.stage.Stage;

public class RunClient extends Application {
    public static void main(String[] args) {
        launch();
    }

    public void start(Stage primaryStage) {
        ClientFactory clientFactory = new ClientFactory();
        Model model = new ModelManager(clientFactory);
        ViewModelFactory viewModelFactory = new ViewModelFactory(model);
        ViewHandler viewHandler = new ViewHandler(viewModelFactory);
        viewHandler.start(primaryStage);
    }
}
