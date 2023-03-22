package com.pastimegames.readysetbet.core.domain.events;

public record RaceFinishedEvent(String winnerHorseName) {

    public static String type(){
        return RaceFinishedEvent.class.getName();
    }
}
