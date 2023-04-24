package com.pastimegames.readysetbet.core.domain.valueobjects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PlayerBalances {

    private final List<PlayerBalance> balances;

    public PlayerBalances(List<PlayerBalance> mapOfBalances) {
        balances = mapOfBalances;
    }


    public int balanceOfPlayer(String playerName) {
        return balances.stream()
                .filter(playerBalance -> playerBalance.playerName().equals(playerName))
                .findFirst()
                .get()
                .balance();
    }

    public Iterable<String> playerNames() {
        return balances.stream()
                .map(PlayerBalance::playerName)
                .collect(Collectors.toList());
    }

}
