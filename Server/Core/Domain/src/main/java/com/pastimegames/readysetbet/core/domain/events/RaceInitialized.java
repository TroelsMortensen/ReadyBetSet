package com.pastimegames.readysetbet.core.domain.events;

public record RaceInitialized() {

    public static String type(){
        return RaceInitialized.class.getName();
    }
}
