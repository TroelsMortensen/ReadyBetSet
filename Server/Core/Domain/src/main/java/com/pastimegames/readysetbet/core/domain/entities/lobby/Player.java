package com.pastimegames.readysetbet.core.domain.entities.lobby;

public class Player {
    private String name;
    private String colorCode;

    private int coins;

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
}
