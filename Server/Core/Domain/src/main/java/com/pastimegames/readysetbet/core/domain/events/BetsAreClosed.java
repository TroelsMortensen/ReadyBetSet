package com.pastimegames.readysetbet.core.domain.events;

public record BetsAreClosed() {

    public static String type(){
        return BetsAreClosed.class.getName();
    }
}
