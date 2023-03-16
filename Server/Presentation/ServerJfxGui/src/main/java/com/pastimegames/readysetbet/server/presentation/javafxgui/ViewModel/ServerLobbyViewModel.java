package com.pastimegames.readysetbet.server.presentation.javafxgui.ViewModel;

import com.pastimegames.readysetbet.server.presentation.javafxgui.Model.Model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.beans.PropertyChangeEvent;

public class ServerLobbyViewModel
{

  private Model model;
  private ObservableList<String> players;

  public ServerLobbyViewModel(Model model)
  {
    players = FXCollections.observableArrayList();
    this.model = model;
    model.addPropertyChangeListener("PLAYER_JOIN", this::playerJoin);
    model.addPropertyChangeListener("PLAYER_LEAVE", this::playerLeave);
  }

  private void playerLeave(PropertyChangeEvent event)
  {
    String name = (String) event.getNewValue();
    players.remove(name);
  }

  private void playerJoin(PropertyChangeEvent event)
  {
    String name = (String) event.getNewValue();
    players.add(name);
  }

  public ObservableList<String> getPlayers()
  {
    return players;
  }
}
