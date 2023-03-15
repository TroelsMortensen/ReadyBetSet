package com.pastimegames.readysetbet.core.domain.events;

public record DiceRolled(int firstDice, int secondDice) {

    public static String type(){
        return DiceRolled.class.getName();
    }
}
