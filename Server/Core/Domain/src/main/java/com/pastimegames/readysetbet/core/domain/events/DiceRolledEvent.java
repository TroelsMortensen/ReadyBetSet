package com.pastimegames.readysetbet.core.domain.events;

public record DiceRolledEvent(int firstDice, int secondDice) {

    public static String type(){
        return DiceRolledEvent.class.getName();
    }
}
