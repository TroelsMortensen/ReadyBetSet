package com.pastimegames.readysetbet.core.domain.valueobjects;

public class DiceRoll {
    private final int firstDice;
    private final int secondDice;

    public DiceRoll(int firstDiceResult, int secondDiceResult) {
        this.firstDice = firstDiceResult;
        this.secondDice = secondDiceResult;
    }

    public int firstDice() {
        return firstDice;
    }

    public int secondDice() {
        return secondDice;
    }

    public String sumAsHorseId() {
        int sum = firstDice + secondDice;
        switch (sum) {
            case 2:
            case 3:
                return "2/3";
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
                return String.valueOf(sum);
            case 11:
            case 12:
                return "11/12";

        }
        throw new RuntimeException("Invalid roll with sum of " + sum);
    }
}
