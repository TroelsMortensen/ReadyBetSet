package com.pastimegames.readysetbet.javafxclient.socketclient.Model;

import com.pastimegames.readysetbet.javafxclient.socketclient.Core.ClientFactory;
import com.pastimegames.readysetbet.javafxclient.socketclient.Events.BetPlacedEvent;
import com.pastimegames.readysetbet.javafxclient.socketclient.Events.PlayerJoinedEvent;
import com.pastimegames.readysetbet.javafxclient.socketclient.Networking.Client;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

public class ModelManager implements Model {


    //The networking interface
    private final Client client;

    //Observable pattern
    private final PropertyChangeSupport support;

    //Local model
    private Player player;
    private String playerNameRequest;


    public ModelManager(ClientFactory clientFactory) {
        client = clientFactory.getClient();
        client.start();

        support = new PropertyChangeSupport(this);

        client.addPropertyChangeListener("PLAYER_JOINED", this::onPlayerJoinedLobby);
        client.addPropertyChangeListener("BET_PLACED", this::onBetPlaced);
        client.addPropertyChangeListener("HORSE_MOVED", this::onHorseMoved);
        client.addPropertyChangeListener("LOBBY_CLOSED", this::onLobbyClosed);
    }

    private void onLobbyClosed(PropertyChangeEvent propertyChangeEvent) {
        support.firePropertyChange("LOBBY_CLOSED", null, null);
    }

    private void onHorseMoved(PropertyChangeEvent propertyChangeEvent) {
        support.firePropertyChange("HORSE_MOVED", null, propertyChangeEvent.getNewValue());
    }

    private void onBetPlaced(PropertyChangeEvent propertyChangeEvent) {
        BetPlacedEvent betPlacedEvent = (BetPlacedEvent) propertyChangeEvent.getNewValue();
        String owningPlayer = betPlacedEvent.owningPlayer();
        if (owningPlayer.equals(player.getName())) {
            for (Coin coin : player.getCoins()) {
                if (coin.getValue() == betPlacedEvent.coinValue()) { //TODO: This won't work when the coin value is shared with special coins (FIX: use ID)
                    if (!coin.isUsed()) {
                        coin.setUsed(true);
                        support.firePropertyChange("COIN_USED", null, coin.getID());
                        break;
                    }
                }
            }
            support.firePropertyChange("BET_ACCEPTED", null, betPlacedEvent);
            return;
        }
        support.firePropertyChange("BET_PLACED", null, betPlacedEvent);

    }

    private void onPlayerJoinedLobby(PropertyChangeEvent propertyChangeEvent) {
        PlayerJoinedEvent playerJoinedEvent = (PlayerJoinedEvent) propertyChangeEvent.getNewValue();
        if (playerNameRequest.equals(playerJoinedEvent.name())) {
            player = new Player(playerJoinedEvent.name(), playerJoinedEvent.colour());
            support.firePropertyChange("PLAYER_JOIN_REQUEST_ACCEPTED", null, player);
        }
    }

    /*
    Model interface implementation
    */
    @Override
    public void joinLobby(String playerNameRequest, String colour) {
        this.playerNameRequest = playerNameRequest;
        client.joinLobby(playerNameRequest, colour);
    }

    @Override
    public void placeBet(int indexOfBet, int value) {
        client.placeBet(indexOfBet, value, player.getName(), player.getColour());
    }

    @Override
    public List<Coin> getCoins() {
        return player.getCoins();
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
