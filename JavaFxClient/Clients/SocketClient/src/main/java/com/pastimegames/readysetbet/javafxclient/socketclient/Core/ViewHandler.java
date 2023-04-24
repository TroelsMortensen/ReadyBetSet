package com.pastimegames.readysetbet.javafxclient.socketclient.Core;

import com.pastimegames.readysetbet.javafxclient.socketclient.View.BettingBoardViewController;
import com.pastimegames.readysetbet.javafxclient.socketclient.View.JoinLobbyViewController;
import com.pastimegames.readysetbet.javafxclient.socketclient.View.RaceViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ViewHandler
{

  private Stage mainStage;
  private final ViewModelFactory viewModelFactory;

  private JoinLobbyViewController joinLobbyViewController;
  private BettingBoardViewController bettingBoardViewController;
  private RaceViewController raceViewController;

  private final Map<String, Scene> scenes;

  public ViewHandler(ViewModelFactory viewModelFactory)
  {
    this.viewModelFactory = viewModelFactory;
    scenes = new HashMap<>();
  }

  public void start(Stage stage)
  {
    mainStage = stage;
    //openRaceView();
    //openBettingView();
    openViewLobby();
  }

  public void openViewLobby()
  {
    if(joinLobbyViewController == null) {
      joinLobbyViewController = loadFromFXML("JoinLobbyView", "Join Lobby");
      joinLobbyViewController.init(this, viewModelFactory.getJoinLobbyViewModel());
    }
    changeScene("Join Lobby");
  }

  public void openViewBetting() {
    if(bettingBoardViewController == null) {
      bettingBoardViewController = loadFromFXML("BettingBoardView", "Betting Board");
      bettingBoardViewController.init(viewModelFactory.getBettingBoardViewModel());
    }
    changeScene("Betting Board");
  }

  public void openViewRace()
  {
    if(raceViewController == null) {
      raceViewController = loadFromFXML("RaceView", "Race");
      raceViewController.init(viewModelFactory.getRaceViewModel());
    }
    changeScene("Race");
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
