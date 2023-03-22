package com.pastimegames.readysetbet.core.domain.events;

public record RaceStartedEvent() {

    public static String type(){
        return RaceStartedEvent.class.getName();
    }
}
