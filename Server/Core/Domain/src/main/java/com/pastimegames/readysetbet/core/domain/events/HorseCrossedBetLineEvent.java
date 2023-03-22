package com.pastimegames.readysetbet.core.domain.events;

public record HorseCrossedBetLineEvent(String horseId) {

    public static String type(){
        return HorseCrossedBetLineEvent.class.getName();
    }
}
