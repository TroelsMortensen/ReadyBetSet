package com.pastimegames.readysetbet.core.domain.events;

public record HorseMovedEvent(String horseName, int currentPosition) {

    public static String type(){
        return HorseMovedEvent.class.getName();
    }
}