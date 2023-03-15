package com.pastimegames.readysetbet.core.domain.entities.lobby;

public class RaceOptions {

    private int moveTickTimeInMs = 1000;
    private int startCoins = 0;
    private int numberOfAllowedBidsPerCell = 1;

    private int numberOfRaces = 4;

    public RaceOptions setMoveTickTimeInMs(int oveTickTimeInMs) {
        this.moveTickTimeInMs = oveTickTimeInMs;
        return this;
    }

    public RaceOptions setStartCoins(int startCoins) {
        this.startCoins = startCoins;
        return this;
    }

    public RaceOptions setNumberOfAllowedBidsPerCell(int numberOfAllowedBidsPerCell) {
        this.numberOfAllowedBidsPerCell = numberOfAllowedBidsPerCell;
        return this;
    }

    public int numberOfRaces() {
        return numberOfRaces;
    }

    public RaceOptions setNumberOfRaces(int numberOfRaces) {
        this.numberOfRaces = numberOfRaces;
        return this;
    }

    public int moveTickTime(){
        return moveTickTimeInMs;
    }

    public int startCoins(){
        return startCoins;
    }

    public int numberOfAllowedBidsPerCell() {
        return numberOfAllowedBidsPerCell;
    }


}
