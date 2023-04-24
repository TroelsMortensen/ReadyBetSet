package com.pastimegames.readysetbet.server.presentation.javafxgui.Model;

import com.pastimegames.readysetbet.core.application.gamemanager.GameManager;
import com.pastimegames.readysetbet.core.domain.entities.lobby.RaceOptions;
import com.pastimegames.readysetbet.core.domain.eventpublisher.DomainEventListener;
import com.pastimegames.readysetbet.core.domain.eventpublisher.DomainEventPublisher;
import com.pastimegames.readysetbet.core.domain.events.HorseMovedEvent;
import com.pastimegames.readysetbet.core.domain.events.PlayerJoinedLobbyEvent;
import com.pastimegames.readysetbet.core.domain.events.PlayerLeftLobbyEvent;
import com.pastimegames.readysetbet.core.domain.events.ResultsCalculatedEvent;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Model implements PropertyChangeSubject
{
  private PropertyChangeSupport propertyChangeSupport;
  private GameManager gameManager;

  public Model(GameManager gameManager)
  {
    this.gameManager = gameManager;
    propertyChangeSupport = new PropertyChangeSupport(this);
    initializeListeners();
  }

  public void playerJoin(String name)
  {
    propertyChangeSupport.firePropertyChange("PLAYER_JOIN", null, name);
  }

  public void playerLeave(String name)
  {
    propertyChangeSupport.firePropertyChange("PLAYER_LEAVE", null, name);
  }

  public void initializeRace(RaceOptions raceOptions)
  {
    gameManager.finalizeLobby(raceOptions);
  }

  public void startRace() {
    gameManager.startRace();
  }

  public void displayResults()
  {
    gameManager.displayResults();
  }

  @Override
  public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
    if(propertyName == null || propertyName.equals(""))
      addPropertyChangeListener(listener);
    else
      propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
  }

  @Override
  public void addPropertyChangeListener(PropertyChangeListener listener) {
    propertyChangeSupport.addPropertyChangeListener(listener);
  }

  @Override
  public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
    if(propertyName == null || propertyName.equals(""))
      removePropertyChangeListener(listener);
    else
      propertyChangeSupport.removePropertyChangeListener(propertyName, listener);
  }

  @Override
  public void removePropertyChangeListener(PropertyChangeListener listener) {
    propertyChangeSupport.removePropertyChangeListener(listener);
  }

  private void initializeListeners() {
    DomainEventPublisher.instance().subscribe(HorseMovedEvent.type(), (DomainEventListener<HorseMovedEvent>) horseMovedEvent -> {
      propertyChangeSupport.firePropertyChange("HORSE_MOVED", null, horseMovedEvent);
    } );
    DomainEventPublisher.instance().subscribe(PlayerJoinedLobbyEvent.type(), (DomainEventListener<PlayerJoinedLobbyEvent>) playerJoinedLobby -> {
      propertyChangeSupport.firePropertyChange("PLAYER_JOIN", null, playerJoinedLobby);
    });
    DomainEventPublisher.instance().subscribe(PlayerLeftLobbyEvent.type(), (DomainEventListener<PlayerLeftLobbyEvent>) playerLeftLobby -> {
      propertyChangeSupport.firePropertyChange("PLAYER_LEAVE", null, playerLeftLobby);
    });
    DomainEventPublisher.instance().subscribe(ResultsCalculatedEvent.type(), (DomainEventListener<ResultsCalculatedEvent>) resultsCalculatedEvent -> {
      propertyChangeSupport.firePropertyChange("RESULTS_CALCULATED", null, resultsCalculatedEvent);
    });
  }

}
