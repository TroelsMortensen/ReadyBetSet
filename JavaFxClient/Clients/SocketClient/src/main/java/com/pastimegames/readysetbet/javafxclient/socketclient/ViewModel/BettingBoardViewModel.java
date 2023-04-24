package com.pastimegames.readysetbet.javafxclient.socketclient.ViewModel;

import com.pastimegames.readysetbet.javafxclient.socketclient.Events.BetPlacedEvent;
import com.pastimegames.readysetbet.javafxclient.socketclient.Model.Coin;
import com.pastimegames.readysetbet.javafxclient.socketclient.Model.Model;
import com.pastimegames.readysetbet.javafxclient.socketclient.ViewModel.ModelRepresentations.CoinRepresentation;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class BettingBoardViewModel {
    private Model model;
    private Consumer<Integer> onBetPlaced;
    private Map<Integer, BooleanProperty> bettingCoinsStatus;
    private Coin selectedCoin;

    public BettingBoardViewModel(Model model)
    {
        this.model = model;
        model.addPropertyChangeListener("BET_ACCEPTED", this::onBetAccepted);
        model.addPropertyChangeListener("COIN_USED", this::onCoinUsed);
        bettingCoinsStatus = new HashMap<>();
        populateBettingCoinsStatus();
    }

    private void onCoinUsed(PropertyChangeEvent propertyChangeEvent) {
        Coin coin = (Coin) propertyChangeEvent.getNewValue();
        bettingCoinsStatus.get(coin.getID()).setValue(true);
    }

    private void populateBettingCoinsStatus() {
        List<Coin> coins = model.getCoins();
        for(Coin coin : coins)
        {
            bettingCoinsStatus.put(coin.getID(), new SimpleBooleanProperty());
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

    public List<Coin> getCoins()
    {
        return model.getCoins();
    }

    public void setSelectedCoin(Coin coin)
    {
        selectedCoin = coin;
    }

    public void setOnBetPlaced(Consumer<Integer> onBetPlaced)
    {
        this.onBetPlaced = onBetPlaced;
    }

    public ObservableValue<Boolean> getIsUsedPropertyForCoinWithID(int id) {
        return bettingCoinsStatus.get(id);
    }
}
