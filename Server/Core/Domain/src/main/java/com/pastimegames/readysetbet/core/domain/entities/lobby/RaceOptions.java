package com.pastimegames.readysetbet.core.domain.entities.lobby;

public class RaceOptions {

    private int moveTickTimeInMs = 1000;
    private int startSaldo = 0;
    private int numberOfAllowedBidsPerCell = 1;

    private int numberOfRaces = 4;

    public RaceOptions setMoveTickTimeInMs(int oveTickTimeInMs) {
        this.moveTickTimeInMs = oveTickTimeInMs;
        return this;
    }

    public RaceOptions setStartSaldo(int startSaldo) {
        this.startSaldo = startSaldo;
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

    public int startSaldo(){
        return startSaldo;
    }

    public int numberOfAllowedBidsPerCell() {
        return numberOfAllowedBidsPerCell;
    }


}
