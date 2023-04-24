package com.pastimegames.readysetbet.core.domain.valueobjects;

import java.util.HashMap;
import java.util.Map;

public class PlayerBalances {

    private Map<String, Integer> balances = new HashMap<>();

    public PlayerBalances(Map<String, Integer> mapOfBalances) {
        balances = mapOfBalances;
    }


    public int getBalanceOfPlayer(String playerName){
        return balances.get(playerName);
    }

    public Iterable<String> getPlayerNames(){
        return balances.keySet();
    }
}
