package com.pastimegames.readysetbet.core.domain.events;

/**
 * Published when the next race is ready, only published for second race and forward.
 * @param raceNumber
 */
public record NextRaceReady(int raceNumber) {

    public static String type(){
        return NextRaceReady.class.getName();
    }
}
