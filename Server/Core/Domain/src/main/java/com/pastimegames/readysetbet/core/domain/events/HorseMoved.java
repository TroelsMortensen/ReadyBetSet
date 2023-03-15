package com.pastimegames.readysetbet.core.domain.events;

public record HorseMoved(String horseName, int currentPosition) {

    public static String type(){
        return HorseMoved.class.getName();
    }
}