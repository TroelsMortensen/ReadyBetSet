package com.pastimegames.readysetbet.core.domain.entities.lobby;

public class RaceOptions {

    private int moveTickTime = 1000;
    private int startCoins = 0;
    private int numberOfAllowedBidsPerCell = 1;

    public RaceOptions setMoveTickTimeInMs(int moveTickTime) {
        this.moveTickTime = moveTickTime;
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

    public int moveTickTime(){
        return moveTickTime;
    }

    public int startCoins(){
        return startCoins;
    }

    public int numberOfAllowedBidsPerCell() {
        return numberOfAllowedBidsPerCell;
    }
}
