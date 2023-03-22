package com.pastimegames.readysetbet.javafxclient.socketclient.ViewModel;

import com.pastimegames.readysetbet.javafxclient.socketclient.Model.Bet;
import com.pastimegames.readysetbet.javafxclient.socketclient.Model.Model;

import java.util.List;

public class BettingBoardViewModel {
    private Model model;
    private PlayerRepresentation player;
    private CoinRepresentation selectedCoin;

    public BettingBoardViewModel(Model model, PlayerRepresentation player)
    {
        this.model = model;
        this.player = player;
    }


    public void placeBet(int bettingPosition) {
        Bet betToPlace = new Bet(bettingPosition, selectedCoin);
        model.placeBet(betToPlace);
    }

    public List<CoinRepresentation> getCoins()
    {
        return player.getCoins();
    }

    public void setSelectedCoin(CoinRepresentation coin)
    {
        selectedCoin = coin;
    }
}
