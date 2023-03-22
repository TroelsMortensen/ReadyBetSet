package com.pastimegames.readysetbet.javafxclient.socketclient.ViewModel;

import com.pastimegames.readysetbet.javafxclient.socketclient.Events.BetPlacedEvent;
import com.pastimegames.readysetbet.javafxclient.socketclient.Model.Model;
import com.pastimegames.readysetbet.javafxclient.socketclient.ViewModel.ModelRepresentations.CoinRepresentation;
import com.pastimegames.readysetbet.javafxclient.socketclient.ViewModel.ModelRepresentations.PlayerRepresentation;

import java.beans.PropertyChangeEvent;
import java.util.List;

public class BettingBoardViewModel {
    private Model model;
    private PlayerRepresentation player;
    private CoinRepresentation selectedCoin;

    public BettingBoardViewModel(Model model, PlayerRepresentation player)
    {
        this.model = model;
        this.player = player;
        model.addPropertyChangeListener("BET_PLACED", this::onBetPlaced);
    }

    private void onBetPlaced(PropertyChangeEvent event)
    {
        BetPlacedEvent betPlacedEvent = (BetPlacedEvent) event.getNewValue();
        if(betPlacedEvent.owningPlayer().equals(player.getName()))
        {
            player.markCoinAsUsed(betPlacedEvent.coinValue());
        }
        //TODO: Mark cell with coin and disable for bets
    }

    public void placeBet(int bettingPosition) {
        model.placeBet(bettingPosition, selectedCoin);
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
