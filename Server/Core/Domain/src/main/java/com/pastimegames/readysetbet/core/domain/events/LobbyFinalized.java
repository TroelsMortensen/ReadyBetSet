package com.pastimegames.readysetbet.core.domain.events;

import com.pastimegames.readysetbet.core.domain.entities.lobby.RaceOptions;

/**
 * published when the server-manager selects to move from lobby to racing
 * @param options
 */
public record LobbyFinalized(RaceOptions options) {

    public static String type(){
        return LobbyFinalized.class.getName();
    }
}
