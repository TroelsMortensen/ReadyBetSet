package com.pastimegames.readysetbet.server.presentation.javafxgui.View;

import com.pastimegames.readysetbet.server.presentation.javafxgui.Core.ViewHandler;
import com.pastimegames.readysetbet.server.presentation.javafxgui.ViewModel.ServerLobbyViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class ServerLobbyViewController
{

  @FXML
  private ListView<String> playerList;

  private ViewHandler viewHandler;
  private ServerLobbyViewModel serverLobbyViewModel;

  public void init(ViewHandler viewHandler, ServerLobbyViewModel serverLobbyViewModel)
  {
    this.viewHandler = viewHandler;
    this.serverLobbyViewModel = serverLobbyViewModel;
    playerList.setItems(serverLobbyViewModel.getPlayers());
  }

  public void onButtonStartRace(ActionEvent actionEvent)
  {
    viewHandler.openRaceView();
    serverLobbyViewModel.initializeRace();
  }

}
