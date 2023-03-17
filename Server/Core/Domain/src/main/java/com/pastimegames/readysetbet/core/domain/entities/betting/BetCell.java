package com.pastimegames.readysetbet.core.domain.entities.betting;

import java.util.ArrayList;
import java.util.List;

public class BetCell {

    private int id;
    private int payoutMultiplyer;
    private int penalty;

    private List<Coin> coins = new ArrayList<>();

    public BetCell(int payoutMultiplyer, int penalty) {
        this.payoutMultiplyer = payoutMultiplyer;
        this.penalty = penalty;
    }

    void setId(int id){
        this.id = id;
    }

    public void placeCoin(Coin coin){
        coins.add(coin);
    }

}
