package com.pastimegames.readysetbet.core.domain.events;

public record NewRaceReady(int raceNumber) {

    public static String type(){
        return NewRaceReady.class.getName();
    }
}
