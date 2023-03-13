package com.pastimegames.readysetbet.core.domain.events;

public record HorseMoved(String horseName, int position) {

    public static String type(){
        return HorseMoved.class.getName();
    }
}
