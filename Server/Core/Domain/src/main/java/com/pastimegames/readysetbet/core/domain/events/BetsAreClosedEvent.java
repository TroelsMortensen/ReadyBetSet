package com.pastimegames.readysetbet.core.domain.events;

public record BetsAreClosedEvent() {

    public static String type(){
        return BetsAreClosedEvent.class.getName();
    }
}
