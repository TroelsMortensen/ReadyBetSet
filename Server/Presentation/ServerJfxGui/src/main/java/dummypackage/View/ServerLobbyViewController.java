package dummypackage.View;

import dummypackage.Core.ViewHandler;
import dummypackage.ViewModel.ServerLobbyViewModel;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;

public class ServerLobbyViewController
{

  @FXML
  private ListView<String> playerList;

  private ViewHandler viewHandler;

  public void init(ViewHandler viewHandler, ServerLobbyViewModel serverLobbyViewModel)
  {
    this.viewHandler = viewHandler;
    playerList.setItems(serverLobbyViewModel.getPlayers());
  }

  public void onButtonStartRace(ActionEvent actionEvent)
  {
    viewHandler.openRaceView();
  }
}
