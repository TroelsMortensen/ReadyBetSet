package com.pastimegames.readysetbet.core.domain.entities.lobby;

public class Player {
    private String name;
    private String colorCode;

    private int coins;

    private int saldo = 0;

    public Player(String name, String colorCode) {
        this.name = name;
        this.colorCode = colorCode;
        coins = 0;
    }

    public String name(){
        return name;
    }

    public String colorCode() {
        return colorCode;
    }

    public void addWinning(int amount){
        saldo += amount;
    }

    public void applyPenalty(int amount){
        saldo -= amount;
        saldo = Math.max(saldo, 0);
    }
}
