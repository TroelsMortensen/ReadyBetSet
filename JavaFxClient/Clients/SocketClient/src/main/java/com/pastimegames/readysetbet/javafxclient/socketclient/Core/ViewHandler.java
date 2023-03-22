package com.pastimegames.readysetbet.javafxclient.socketclient.Core;

import com.pastimegames.readysetbet.javafxclient.socketclient.RunClient;
import com.pastimegames.readysetbet.javafxclient.socketclient.View.BettingBoardViewController;
import com.pastimegames.readysetbet.javafxclient.socketclient.View.JoinLobbyViewController;
import com.pastimegames.readysetbet.javafxclient.socketclient.View.RaceViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewHandler
{

  private Stage mainStage;
  private ViewModelFactory viewModelFactory;

  public ViewHandler(ViewModelFactory viewModelFactory)
  {
    this.viewModelFactory = viewModelFactory;
  }

  public void start(Stage stage)
  {
    mainStage = stage;
    //openRaceView();
    //openViewLobby();
    openBettingView();
  }

  private void openBettingView() {
    FXMLLoader fxmlLoader = new FXMLLoader(
            RunClient.class.getResource("BettingBoardView.fxml"));
    Scene scene = null;
    try
    {
      scene = new Scene(fxmlLoader.load(), 500, 500);
    }
    catch (IOException e)
    {
      throw new RuntimeException(e);
    }
    BettingBoardViewController bettingBoardViewController = fxmlLoader.getController();
    bettingBoardViewController.init(this, viewModelFactory.getBettingBoardViewModel());
    mainStage.setTitle("Lobby");
    mainStage.setScene(scene);
    mainStage.show();
  }

  public void openViewLobby()
  {
    FXMLLoader fxmlLoader = new FXMLLoader(
        RunClient.class.getResource("JoinLobbyView.fxml"));
    Scene scene = null;
    try
    {
      scene = new Scene(fxmlLoader.load(), 500, 500);
    }
    catch (IOException e)
    {
      throw new RuntimeException(e);
    }
    JoinLobbyViewController joinLobbyViewController = fxmlLoader.getController();
    joinLobbyViewController.init(this, viewModelFactory.getJoinLobbyViewModel());
    mainStage.setTitle("Lobby");
    mainStage.setScene(scene);
    mainStage.show();
  }

  public void openRaceView()
  {
    FXMLLoader fxmlLoader = new FXMLLoader(
        RunClient.class.getResource("RaceView.fxml"));
    Scene scene = null;
    try
    {
      scene = new Scene(fxmlLoader.load(), 600, 600);
    }
    catch (IOException e)
    {
      throw new RuntimeException(e);
    }
    RaceViewController raceViewController = fxmlLoader.getController();
    raceViewController.init(viewModelFactory.getRaceViewModel());
    mainStage.setTitle("Race");
    mainStage.setScene(scene);
    mainStage.show();
  }
}
