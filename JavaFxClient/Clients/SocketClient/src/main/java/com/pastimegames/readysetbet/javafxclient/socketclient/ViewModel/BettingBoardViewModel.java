package com.pastimegames.readysetbet.javafxclient.socketclient.ViewModel;

import com.pastimegames.readysetbet.javafxclient.socketclient.Events.BetPlacedEvent;
import com.pastimegames.readysetbet.javafxclient.socketclient.Model.Model;
import com.pastimegames.readysetbet.javafxclient.socketclient.ViewModel.ModelRepresentations.CoinRepresentation;
import com.pastimegames.readysetbet.javafxclient.socketclient.ViewModel.ModelRepresentations.PlayerRepresentation;

import java.beans.PropertyChangeEvent;
import java.util.List;
import java.util.function.Consumer;

public class BettingBoardViewModel {
    private Model model;
    private PlayerRepresentation player;
    private CoinRepresentation selectedCoin;
    private Consumer<Integer> onBetPlaced;

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
        onBetPlaced.accept(betPlacedEvent.index());
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

    public void setOnBetPlaced(Consumer<Integer> onBetPlaced)
    {
        this.onBetPlaced = onBetPlaced;
    }
}
