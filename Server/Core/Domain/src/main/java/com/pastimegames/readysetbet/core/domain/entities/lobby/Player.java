package com.pastimegames.readysetbet.core.domain.entities.lobby;

import com.pastimegames.readysetbet.core.domain.exceptions.DomainLogicException;

public class Player {
    private String name;
    private String colorCode;


    private int balance = 0;

    public Player(String name, String colorCode) {
        this.name = name;
        this.colorCode = colorCode;
        balance = 0;
        validate();
    }

    private void validate() {
        if (name == null || "".equals(name)) throw new DomainLogicException("Player name cannot be empty");
        if (colorCode == null || "".equals(colorCode)) throw new DomainLogicException("Coin color cannot be empty");
    }

    public String name() {
        return name;
    }

    public String colorCode() {
        return colorCode;
    }

    public void addWinning(int amount) {
        balance += amount;
    }

    public void applyPenalty(int amount) {
        balance -= amount;
        balance = Math.max(balance, 0);
    }

    public int balance() {
        return balance;
    }
}
