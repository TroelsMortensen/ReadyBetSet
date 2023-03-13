package com.pastimegames.readysetbet.core.domain.valueobjects;

public class RaceOptions {

    private int moveTickTime = 1000;

    public RaceOptions setMoveTickTimeInMs(int moveTickTime) {
        this.moveTickTime = moveTickTime;
        return this;
    }

    public int moveTickTime(){
        return moveTickTime;
    }
}
