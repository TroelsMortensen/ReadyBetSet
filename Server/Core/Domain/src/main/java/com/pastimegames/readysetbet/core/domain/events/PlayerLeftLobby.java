package com.pastimegames.readysetbet.core.domain.events;

public record PlayerLeftLobby(String name) {
    public static String type(){
        return PlayerLeftLobby.class.getName();
    }
}
