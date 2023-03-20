package com.pastimegames.readysetbet.server.presentation.javafxgui.View;

import com.pastimegames.readysetbet.server.presentation.javafxgui.Core.ViewHandler;
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
