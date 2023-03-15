package dummypackage.View;

import dummypackage.Core.ViewHandler;
import javafx.event.ActionEvent;

public class ServerMainViewController
{

  private ViewHandler viewHandler;

  public void init(ViewHandler viewHandler)
  {
    this.viewHandler = viewHandler;
  }

  public void onButtonStart(ActionEvent actionEvent)
  {

      viewHandler.openViewLobby();

  }
}
