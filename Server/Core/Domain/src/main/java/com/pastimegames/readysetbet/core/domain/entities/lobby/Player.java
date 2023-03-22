package com.pastimegames.readysetbet.core.domain.entities.lobby;

public class Player {
    private String name;
    private String colorCode;


    private int balance = 0;

    public Player(String name, String colorCode) {
        this.name = name;
        this.colorCode = colorCode;
        balance = 0;
    }

    public String name(){
        return name;
    }

    public String colorCode() {
        return colorCode;
    }

    public void addWinning(int amount){
        balance += amount;
    }

    public void applyPenalty(int amount){
        balance -= amount;
        balance = Math.max(balance, 0);
    }

    public int balance() {
        return balance;
    }
}
