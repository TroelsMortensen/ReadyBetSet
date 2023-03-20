package com.pastimegames.readysetbet.javafxclient.socketclient;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class RunClient extends Application {
    public static void main(String[] args) {
        launch();
    }

    public void start(Stage primaryStage)  {
        System.out.println(getClass().getResource(""));
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("RaceView.fxml"));
        Scene scene = null;
        try
        {
            scene = new Scene(fxmlLoader.load(), 600, 600);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
       // RaceViewController raceViewController = fxmlLoader.getController();
        //raceViewController.init(viewModelFactory.getRaceViewModel());
        primaryStage.setTitle("Lobby");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
