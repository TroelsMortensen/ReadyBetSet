package com.pastimegames.readysetbet.javafxclient.socketclient.Model;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private String colour;
    private List<Coin> coins;


    public Player(String name, String colour) {
        this.name = name;
        this.colour = colour;
        coins = new ArrayList<>();
        initializeCoins();
    }

    private void initializeCoins() {
        //TODO: Ensure ID's are unique
        coins.add(new Coin(2, false, 1));
        coins.add(new Coin(3, false, 2));
        coins.add(new Coin(3, false, 3));
        coins.add(new Coin(4, false, 4));
        coins.add(new Coin(5, false, 5));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public List<Coin> getCoins() {
        return coins;
    }
}
