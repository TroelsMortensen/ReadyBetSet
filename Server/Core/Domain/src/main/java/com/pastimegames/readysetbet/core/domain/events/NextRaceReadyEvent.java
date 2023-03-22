package com.pastimegames.readysetbet.core.domain.events;

/**
 * Published when the next race is ready, only published for second race and forward.
 * @param raceNumber
 */
public record NextRaceReadyEvent(int raceNumber) {

    public static String type(){
        return NextRaceReadyEvent.class.getName();
    }
}
