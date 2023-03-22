package com.pastimegames.readysetbet.core.domain.entities.betting;

import com.pastimegames.readysetbet.core.domain.exceptions.DomainLogicException;

public class Coin {

    private int value;
    private String owningPlayer;
    private String color;

    public Coin(int value, String owningPlayer, String color) {
        this.value = value;
        this.owningPlayer = owningPlayer;
        this.color = color;
        validate();
    }

    private void validate() {
        if(owningPlayer == null || "".equals(owningPlayer)) throw new DomainLogicException("Coin cannot be created without owner");
        if(color == null || "".equals(color)) throw new DomainLogicException("Coin color cannot be empty");
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
