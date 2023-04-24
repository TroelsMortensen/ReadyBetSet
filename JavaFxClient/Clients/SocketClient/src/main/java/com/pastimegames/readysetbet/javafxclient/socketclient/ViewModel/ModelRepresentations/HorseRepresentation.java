package com.pastimegames.readysetbet.javafxclient.socketclient.ViewModel.ModelRepresentations;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class HorseRepresentation
{
  private final StringProperty name;
  private final IntegerProperty position;

  public HorseRepresentation(String name, int position)
  {
    this.name = new SimpleStringProperty();
    this.position = new SimpleIntegerProperty();
    this.name.set(name);
    this.position.set(position);
  }

  public String getName()
  {
    return name.get();
  }

  public IntegerProperty getPositionProperty()
  {
    return position;
  }

  public void setPosition(int position)
  {
    this.position.set(position);
  }
}
