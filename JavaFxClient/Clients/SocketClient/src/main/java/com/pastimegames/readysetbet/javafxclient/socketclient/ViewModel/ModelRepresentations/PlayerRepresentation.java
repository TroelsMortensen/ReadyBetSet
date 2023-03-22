package com.pastimegames.readysetbet.javafxclient.socketclient.ViewModel.ModelRepresentations;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;

import java.util.List;

public class PlayerRepresentation
{

  private StringProperty name;
  private StringProperty colour;
  private List<CoinRepresentation> coins;

  public PlayerRepresentation(String name, String colour)
  {
    this.name = new SimpleStringProperty();
    this.colour = new SimpleStringProperty();

    //FOR TESTING PURPOSES NAME AND COLOR IS SET
    this.name.set("DEFAULT");
    this.colour.set("BLACK");
    //this.name.set(name);
    //this.colour.set(colour);
    //------------------------------------------
    coins = FXCollections.observableArrayList();
    initializeCoins();
  }

  private void initializeCoins()
  {
    coins.add(new CoinRepresentation(2, name.get(), colour.get()));
    coins.add(new CoinRepresentation(3, name.get(), colour.get()));
    coins.add(new CoinRepresentation(3, name.get(), colour.get()));
    coins.add(new CoinRepresentation(4, name.get(), colour.get()));
    coins.add(new CoinRepresentation(5, name.get(), colour.get()));
  }

  public String getName()
  {
    return name.get();
  }

  public StringProperty nameProperty()
  {
    return name;
  }

  public String getColour()
  {
    return colour.get();
  }

  public StringProperty colourProperty()
  {
    return colour;
  }

  public List<CoinRepresentation> getCoins()
  {
    return coins;
  }

  public void markCoinAsUsed(int valueOfCoin)
  {
    for (CoinRepresentation coin : coins)
    {
      if (coin.getValue() == valueOfCoin && coin.isUsed().get() == false)
      {
        coin.isUsed().set(true);
        break;
      }
    }
  }
}
