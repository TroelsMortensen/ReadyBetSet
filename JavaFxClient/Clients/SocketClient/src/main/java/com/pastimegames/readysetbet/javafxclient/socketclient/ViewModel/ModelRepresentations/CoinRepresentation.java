package com.pastimegames.readysetbet.javafxclient.socketclient.ViewModel.ModelRepresentations;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class CoinRepresentation
{

  private int value;
  private BooleanProperty isUsed;

  public CoinRepresentation(int value)
  {
    this.value = value;
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
}
