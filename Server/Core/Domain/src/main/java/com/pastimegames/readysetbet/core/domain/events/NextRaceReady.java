package com.pastimegames.readysetbet.core.domain.events;

public record NextRaceReady(int raceNumber) {

    public static String type(){
        return NextRaceReady.class.getName();
    }
}
