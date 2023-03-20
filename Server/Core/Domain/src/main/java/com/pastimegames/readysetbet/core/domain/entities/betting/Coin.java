package com.pastimegames.readysetbet.core.domain.entities.betting;

public class Coin {

    private int value;
    private String owningPlayer;
    private String color;

    public Coin(int value, String owningPlayer, String color) {
        this.value = value;
        this.owningPlayer = owningPlayer;
        this.color = color;
    }

    public int value() {
        return value;
    }

    public String owningPlayer() {
        return owningPlayer;
    }

    public String color() {
        return color;
    }
}
