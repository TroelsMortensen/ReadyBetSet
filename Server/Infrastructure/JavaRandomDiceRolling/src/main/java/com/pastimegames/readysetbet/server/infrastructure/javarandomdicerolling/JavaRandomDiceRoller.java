package com.pastimegames.readysetbet.server.infrastructure.javarandomdicerolling;

import com.pastimegames.readysetbet.core.domain.domainservices.DiceRoller;
import com.pastimegames.readysetbet.core.domain.valueobjects.DiceRoll;

import java.util.Random;

public class JavaRandomDiceRoller implements DiceRoller {
    private Random random = new Random(1);//TODO: Change back to actual randomness //System.currentTimeMillis());
    @Override
    public DiceRoll rollDice() {
        int firstRoll = random.nextInt(6) + 1;
        int secondRoll = random.nextInt(6) + 1;
        DiceRoll dr = new DiceRoll(firstRoll, secondRoll);
        return dr;
    }
}
