package com.pastimegames.readysetbet.core.domain.entities.betting;

public class Coin {

    private int value;
    private String owningPlayer;

    public Coin(int value, String owningPlayer) {
        this.value = value;
        this.owningPlayer = owningPlayer;
    }

    public int value() {
        return value;
    }

    public String owningPlayer() {
        return owningPlayer;
    }
}
