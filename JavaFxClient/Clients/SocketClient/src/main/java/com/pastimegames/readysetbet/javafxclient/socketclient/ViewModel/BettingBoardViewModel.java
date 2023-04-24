package com.pastimegames.readysetbet.javafxclient.socketclient.ViewModel;

import com.pastimegames.readysetbet.javafxclient.socketclient.Events.BetPlacedEvent;
import com.pastimegames.readysetbet.javafxclient.socketclient.Model.Coin;
import com.pastimegames.readysetbet.javafxclient.socketclient.Model.Model;
import com.pastimegames.readysetbet.javafxclient.socketclient.ViewModel.ModelRepresentations.CoinRepresentation;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class BettingBoardViewModel {
    private final Model model;

    private final List<CoinRepresentation> coinRepresentations;
    private CoinRepresentation selectedCoin;

    private Consumer<Integer> onBetPlaced;

    public BettingBoardViewModel(Model model)
    {
        this.model = model;
        coinRepresentations = new ArrayList<>();
        populateBettingCoinsStatus();
        initializeListeners();
    }

    private void initializeListeners() {
        model.addPropertyChangeListener("BET_ACCEPTED", this::onBetAccepted);
        model.addPropertyChangeListener("BET_PLACED", this::onBetPlaced);
        model.addPropertyChangeListener("COIN_USED", this::onCoinUsed);
    }

    private void populateBettingCoinsStatus() {
        List<Coin> coins = model.getCoins();
        for(Coin coin : coins)
        {
            CoinRepresentation coinRepresentation = new CoinRepresentation(coin.getValue(), coin.getID());
            coinRepresentations.add(coinRepresentation);
        }
    }

    private void onBetPlaced(PropertyChangeEvent propertyChangeEvent) {
        BetPlacedEvent betPlacedEvent = (BetPlacedEvent) propertyChangeEvent.getNewValue();
        onBetPlaced.accept(betPlacedEvent.index()); //Fire event to the view using Java Consumer functional interface
    }

    private void onCoinUsed(PropertyChangeEvent propertyChangeEvent) {
        int coinID = (int) propertyChangeEvent.getNewValue();
        for(CoinRepresentation coinRepresentation : coinRepresentations)
        {
            if(coinRepresentation.getID() == coinID)
            {
                coinRepresentation.getIsUsedProperty().set(true);
                break;
            }
        }
    }

    private void onBetAccepted(PropertyChangeEvent propertyChangeEvent) {
        BetPlacedEvent betPlacedEvent = (BetPlacedEvent) propertyChangeEvent.getNewValue();
        onBetPlaced.accept(betPlacedEvent.index());
        selectedCoin = null;
    }

    public void placeBet(int bettingPosition) {
        if(selectedCoin == null)
        {
            //TODO: Show error message in a popup
            System.out.println("No betting chip selected");
            return;
        }
        model.placeBet(bettingPosition, selectedCoin.getValue());
    }

    public void setSelectedCoin(CoinRepresentation coin)
    {
        selectedCoin = coin;
    }

    public void setOnBetPlaced(Consumer<Integer> onBetPlaced)
    {
        this.onBetPlaced = onBetPlaced;
    }

    public List<CoinRepresentation> getCoinRepresentations() {
        return coinRepresentations;
    }
}
