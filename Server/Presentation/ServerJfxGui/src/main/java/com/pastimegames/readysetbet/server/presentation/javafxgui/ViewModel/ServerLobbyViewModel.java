package com.pastimegames.readysetbet.server.presentation.javafxgui.ViewModel;

import com.pastimegames.readysetbet.core.domain.entities.lobby.RaceOptions;
import com.pastimegames.readysetbet.core.domain.events.PlayerJoinedLobby;
import com.pastimegames.readysetbet.core.domain.events.PlayerLeftLobby;
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
    this.model.addPropertyChangeListener("PLAYER_JOIN", this::playerJoin);
    this.model.addPropertyChangeListener("PLAYER_LEAVE", this::playerLeave);
  }

  private void playerLeave(PropertyChangeEvent event)
  {
    String name = ((PlayerLeftLobby) event.getNewValue()).name();
    players.remove(name);
  }

  private void playerJoin(PropertyChangeEvent event)
  {
    String name = ((PlayerJoinedLobby) event.getNewValue()).name();
    players.add(name);
  }

  public ObservableList<String> getPlayers()
  {
    return players;
  }

  public void initializeRace()
  {
    model.initializeRace(new RaceOptions());
  }
}
