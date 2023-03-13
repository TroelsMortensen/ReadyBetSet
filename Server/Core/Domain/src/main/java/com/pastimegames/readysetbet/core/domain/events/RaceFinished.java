package com.pastimegames.readysetbet.core.domain.events;

public record RaceFinished(String winnerHorseName) {

    public static String type(){
        return RaceFinished.class.getName();
    }
}
