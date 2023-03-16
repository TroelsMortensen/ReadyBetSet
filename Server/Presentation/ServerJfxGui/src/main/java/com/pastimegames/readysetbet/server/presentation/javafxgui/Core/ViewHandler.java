package com.pastimegames.readysetbet.server.presentation.javafxgui.Core;

import com.pastimegames.readysetbet.server.presentation.javafxgui.View.RaceViewController;
import com.pastimegames.readysetbet.server.presentation.javafxgui.View.ServerLobbyViewController;
import com.pastimegames.readysetbet.server.presentation.javafxgui.View.ServerMainViewController;
import com.pastimegames.readysetbet.server.presentation.javafxgui.RunServerGui;
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
    openViewLobby();
  }

  public void openViewMain()
  {
    FXMLLoader fxmlLoader = new FXMLLoader(
        RunServerGui.class.getResource("ServerMainView.fxml"));
    Scene scene = null;
    try
    {
      scene = new Scene(fxmlLoader.load(), 320, 240);
    }
    catch (IOException e)
    {
      throw new RuntimeException(e);
    }
    ServerMainViewController mainViewController = fxmlLoader.getController();
    mainViewController.init(this);
    mainStage.setTitle("Main");
    mainStage.setScene(scene);
    mainStage.show();
  }

  public void openViewLobby()
  {
    FXMLLoader fxmlLoader = new FXMLLoader(
        RunServerGui.class.getResource("ServerLobbyView.fxml"));
    Scene scene = null;
    try
    {
      scene = new Scene(fxmlLoader.load(), 320, 240);
    }
    catch (IOException e)
    {
      throw new RuntimeException(e);
    }
    ServerLobbyViewController serverLobbyViewController = fxmlLoader.getController();
    serverLobbyViewController.init(this, viewModelFactory.getServerLobbyViewModel());
    mainStage.setTitle("Lobby");
    mainStage.setScene(scene);
    mainStage.show();
  }

  public void openRaceView()
  {
    FXMLLoader fxmlLoader = new FXMLLoader(
        RunServerGui.class.getResource("RaceView.fxml"));
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
    mainStage.setTitle("Lobby");
    mainStage.setScene(scene);
    mainStage.show();
  }
}
