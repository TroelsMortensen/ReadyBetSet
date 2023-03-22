package com.pastimegames.readysetbet.core.domain.events;

public record BetPlacedOnCellEvent(int index, int coinValue, String owningPlayer, String color) {

    public static String type(){
        return BetPlacedOnCellEvent.class.getName();
    }
}
