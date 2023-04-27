package com.pastimegames.readysetbet.javafxclient.socketclient.ViewModel;

import com.pastimegames.readysetbet.javafxclient.socketclient.Events.BetPlacedEvent;
import com.pastimegames.readysetbet.javafxclient.socketclient.Model.Coin;
import com.pastimegames.readysetbet.javafxclient.socketclient.Model.Model;
import com.pastimegames.readysetbet.javafxclient.socketclient.Model.PropertyChangeSubject;
import com.pastimegames.readysetbet.javafxclient.socketclient.ViewModel.ModelRepresentations.CoinRepresentation;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class BettingBoardViewModel implements PropertyChangeSubject {
    private final Model model;

    private final List<CoinRepresentation> coinRepresentations;
    private CoinRepresentation selectedCoin;

    private PropertyChangeSupport support;

    public BettingBoardViewModel(Model model)
    {
        this.model = model;
        coinRepresentations = new ArrayList<>();
        support = new PropertyChangeSupport(this);

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
        support.firePropertyChange("BET_PLACED_ON_INDEX", null, betPlacedEvent.index());
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
        support.firePropertyChange("BET_PLACED_ON_INDEX", null, betPlacedEvent.index());
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

    public List<CoinRepresentation> getCoinRepresentations() {
        return coinRepresentations;
    }

    /*
    PropertyChangeSubject interface implementation
     */
    @Override
    public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        if (propertyName == null || propertyName.equals(""))
            addPropertyChangeListener(listener);
        else
            support.addPropertyChangeListener(propertyName, listener);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    @Override
    public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        if (propertyName == null || propertyName.equals(""))
            removePropertyChangeListener(listener);
        else
            support.removePropertyChangeListener(propertyName, listener);
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }
}
