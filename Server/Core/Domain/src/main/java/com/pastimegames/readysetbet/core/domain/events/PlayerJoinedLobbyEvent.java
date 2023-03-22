package com.pastimegames.readysetbet.core.domain.events;

public record PlayerJoinedLobbyEvent(String name, String colorCode) {

    public static String type(){
        return PlayerJoinedLobbyEvent.class.getName();
    }
}
