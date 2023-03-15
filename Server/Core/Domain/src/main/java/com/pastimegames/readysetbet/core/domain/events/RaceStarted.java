package com.pastimegames.readysetbet.core.domain.events;

public record RaceStarted() {

    public static String type(){
        return RaceStarted.class.getName();
    }
}
