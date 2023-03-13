package com.pastimegames.readysetbet.core.domain.events;

public record PlayerJoinedLobby(String name, String colorCode) {

    public static String type(){
        return PlayerJoinedLobby.class.getName();
    }
}
