package com.pastimegames.readysetbet.server.presentation.javafxgui.Core;

import com.pastimegames.readysetbet.server.presentation.javafxgui.View.RaceViewController;
import com.pastimegames.readysetbet.server.presentation.javafxgui.View.ResultsViewController;
import com.pastimegames.readysetbet.server.presentation.javafxgui.View.ServerLobbyViewController;
import com.pastimegames.readysetbet.server.presentation.javafxgui.View.ServerMainViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ViewHandler {

    private Stage mainStage;
    private ViewModelFactory viewModelFactory;

    private ServerMainViewController serverMainViewController;
    private ServerLobbyViewController serverLobbyViewController;
    private RaceViewController raceViewController;
    private ResultsViewController resultsViewController;

    private Map<String, Scene> scenes;

    public ViewHandler(ViewModelFactory viewModelFactory) {
        this.viewModelFactory = viewModelFactory;
        scenes = new HashMap<>();
    }

    public void start(Stage stage) {
        mainStage = stage;
        //openRaceView();
        //openResultsView();
        //openViewLobby();
        openViewMain();
    }

    public void openViewMain() {
        if(serverMainViewController == null) {
            serverMainViewController = loadFromFXML("ServerMainView", "Main");
            serverMainViewController.init(this);
        }
        changeScene("Main");
    }

    public void openViewLobby() {
        if(serverLobbyViewController == null) {
            serverLobbyViewController = loadFromFXML("ServerLobbyView", "Lobby");
            serverLobbyViewController.init(this, viewModelFactory.getServerLobbyViewModel());
        }
        changeScene("Lobby");
    }

    public void openRaceView() {
        if(raceViewController == null) {
            raceViewController = loadFromFXML("RaceView", "Race");
            raceViewController.init(this, viewModelFactory.getRaceViewModel());
        }
        changeScene("Race");
    }

    public void openResultsView() {
        if(resultsViewController == null) {
            resultsViewController = loadFromFXML("ResultsView", "Results");
            resultsViewController.init(this, viewModelFactory.getResultsViewModel());
        }
        changeScene("Results");
    }

    private void changeScene(String title) {
        mainStage.setTitle(title);
        mainStage.setScene(scenes.get(title));
        mainStage.show();
    }

    private <T> T loadFromFXML(String path, String title) {
        FXMLLoader fxmlLoader = null;
        try {
            fxmlLoader = new FXMLLoader(ViewHandler.class.getResource("../" + path + ".fxml"));
            if(scenes.get(title) == null)
            {
                Scene scene = new Scene(fxmlLoader.load(), 500, 500);
                scenes.put(title, scene);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fxmlLoader.getController();
    }




}
