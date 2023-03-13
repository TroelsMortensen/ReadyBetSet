package com.pastimegames.readysetbet.core.domain.events;

public record RaceFinished(String name) {

    public static String type(){
        return RaceFinished.class.getName();
    }
}
