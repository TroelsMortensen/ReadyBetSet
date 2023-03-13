package com.pastimegames.readysetbet.core.domain.events;

public record HorseCrossedBetLine(String horseId) {

    public static String type(){
        return HorseCrossedBetLine.class.getName();
    }
}
