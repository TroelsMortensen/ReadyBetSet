package com.pastimegames.readysetbet.core.domain.events;

import com.pastimegames.readysetbet.core.domain.entities.lobby.RaceOptions;

public record RaceInitialized(RaceOptions options) {

    public static String type(){
        return RaceInitialized.class.getName();
    }
}
