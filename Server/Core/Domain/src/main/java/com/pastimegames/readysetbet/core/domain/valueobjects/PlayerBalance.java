package com.pastimegames.readysetbet.core.domain.valueobjects;

public class PlayerBalance{
    private int balance;
    private String playerName;

    public PlayerBalance(int balance, String playerName) {
        this.balance = balance;
        this.playerName = playerName;
    }

    public int balance() {
        return balance;
    }

    public String playerName() {
        return playerName;
    }
}
