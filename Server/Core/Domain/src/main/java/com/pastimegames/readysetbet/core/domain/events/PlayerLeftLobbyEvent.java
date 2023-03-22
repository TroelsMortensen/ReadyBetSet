package com.pastimegames.readysetbet.core.domain.events;

public record PlayerLeftLobbyEvent(String name) {
    public static String type(){
        return PlayerLeftLobbyEvent.class.getName();
    }
}
