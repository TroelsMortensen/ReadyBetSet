package com.pastimegames.readysetbet.core.domain.events;

public record BetPlacedOnCell(int index, int coinValue, String owningPlayer, String color) {

    public static String type(){
        return BetPlacedOnCell.class.getName();
    }
}
