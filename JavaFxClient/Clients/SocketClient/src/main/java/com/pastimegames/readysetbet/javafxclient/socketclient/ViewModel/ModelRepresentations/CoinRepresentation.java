package com.pastimegames.readysetbet.javafxclient.socketclient.ViewModel.ModelRepresentations;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class CoinRepresentation
{

  private int value;
  private BooleanProperty isUsed;
  private String playerName;
  private String colour;

  public CoinRepresentation(int value, String playerName, String colour)
  {
    this.value = value;
    this.playerName = playerName;
    this.colour = colour;
    this.isUsed = new SimpleBooleanProperty();
    this.isUsed.set(false);
  }

  public int getValue()
  {
    return value;
  }

  public BooleanProperty isUsed()
  {
      return isUsed;
  }

  public String getPlayerName()
  {
    return playerName;
  }

  public String getColour()
  {
    return colour;
  }
}
