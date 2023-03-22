package com.pastimegames.readysetbet.core.domain.entities.betting.propcards;

import com.pastimegames.readysetbet.core.domain.entities.race.Race;
import com.pastimegames.readysetbet.core.domain.valueobjects.RaceResult;

import java.util.function.Function;

public class PropCard {
    private String cardName;
    private Function<RaceResult, Boolean> checkForWin;

    private int payoutMultiplyer;
    private int penalty;

    public interface PropCardWinLogic{
        boolean isWin(Race race);
    }

    public PropCard(String cardName, int payoutMultiplyer, int penalty, Function<RaceResult, Boolean> checkForWin){
        this.cardName = cardName;
        this.checkForWin = checkForWin;
        this.payoutMultiplyer = payoutMultiplyer;
        this.penalty = penalty;
    }

    public boolean hasWon(RaceResult race){
        return checkForWin.apply(race);
    }

    public boolean hasPenalty(RaceResult race){
        return !hasWon(race);
    }

    public String cardName() {
        return cardName;
    }

    public int payoutMultiplyer() {
        return payoutMultiplyer;
    }

    public int penalty() {
        return penalty;
    }
}
