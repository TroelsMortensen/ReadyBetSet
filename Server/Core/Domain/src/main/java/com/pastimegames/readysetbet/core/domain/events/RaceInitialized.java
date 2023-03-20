package com.pastimegames.readysetbet.core.domain.events;

import com.pastimegames.readysetbet.core.domain.entities.lobby.RaceOptions;

/**
 * published when the server-manager selects to move from lobby to racing
 * @param options
 */
public record RaceInitialized(RaceOptions options) {

    public static String type(){
        return RaceInitialized.class.getName();
    }
}
