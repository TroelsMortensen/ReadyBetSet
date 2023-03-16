package com.pastimegames.readysetbet.server.presentation.javafxgui.ViewModel;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ViewModelHorse
{
  private StringProperty name;
  private IntegerProperty position;

  public ViewModelHorse(String name, int position)
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

  public StringProperty nameProperty()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name.set(name);
  }

  public int getPosition()
  {
    return position.get();
  }

  public IntegerProperty positionProperty()
  {
    return position;
  }

  public void setPosition(int position)
  {
    this.position.set(position);
  }
}
