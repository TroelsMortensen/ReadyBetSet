package com.pastimegames.readysetbet.javafxclient.socketclient.Networking;

import com.pastimegames.readysetbet.javafxclient.socketclient.Events.BetPlacedEvent;
import com.pastimegames.readysetbet.javafxclient.socketclient.Events.PlayerJoinedEvent;
import com.pastimegames.readysetbet.javafxclient.socketclient.Model.Coin;
import com.pastimegames.readysetbet.javafxclient.socketclient.Model.Model;
import com.pastimegames.readysetbet.javafxclient.socketclient.Model.Player;
import com.pastimegames.readysetbet.javafxclient.socketclient.ViewModel.ModelRepresentations.CoinRepresentation;
import com.pastimegames.shared.datatransferobjects.BetDto;
import com.pastimegames.shared.datatransferobjects.CoinDto;
import com.pastimegames.shared.datatransferobjects.PlayerDto;
import com.pastimegames.shared.datatransferobjects.socketmessages.SocketDto;
import com.pastimegames.shared.datatransferobjects.socketmessages.SocketMessages;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

public class NetworkModel implements Model {

    private ClientConnection clientConnection;
    private Player player;
    private String playerNameRequest;
    private PropertyChangeSupport support;

    public NetworkModel() {
        support = new PropertyChangeSupport(this);

        Socket socket = null;
        try {
            socket = new Socket("localhost", 2910);
            clientConnection = new ClientConnection(socket);
            new Thread(clientConnection).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        clientConnection.addPropertyChangeListener("PLAYER_JOINED", this::onPlayerJoinedLobby);
        clientConnection.addPropertyChangeListener("BET_PLACED", this::onBetPlaced);
        clientConnection.addPropertyChangeListener("HORSE_MOVED", this::onHorseMoved);
        clientConnection.addPropertyChangeListener("LOBBY_CLOSED", this::onLobbyClosed);
    }

    private void onLobbyClosed(PropertyChangeEvent propertyChangeEvent) {
        support.firePropertyChange("LOBBY_CLOSED", null, null);
    }

    private void onHorseMoved(PropertyChangeEvent propertyChangeEvent) {
        support.firePropertyChange("HORSE_MOVED", null, propertyChangeEvent.getNewValue());
    }

    private void onBetPlaced(PropertyChangeEvent propertyChangeEvent) {
        BetPlacedEvent betPlacedEvent = (BetPlacedEvent) propertyChangeEvent.getNewValue();
        if (betPlacedEvent.owningPlayer().equals(player.getName())) {
            for (Coin coin : player.getCoins()) {
                if (coin.getValue() == betPlacedEvent.coinValue()) {
                    if (coin.isUsed() == false) {
                        coin.setUsed(true);
                        support.firePropertyChange("COIN_USED", null, coin);
                        break;
                    }
                }
            }
        }
        support.firePropertyChange("BET_ACCEPTED", null, betPlacedEvent);
    }

    private void onPlayerJoinedLobby(PropertyChangeEvent propertyChangeEvent) {
        PlayerJoinedEvent playerJoinedEvent = (PlayerJoinedEvent) propertyChangeEvent.getNewValue();
        if (playerNameRequest.equals(playerJoinedEvent.name())) {
            player = new Player(playerJoinedEvent.name(), playerJoinedEvent.colour());
            support.firePropertyChange("PLAYER_JOIN_REQUEST_ACCEPTED", null, player);
        }
    }

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

    @Override
    public void joinLobby(String playerNameRequest, String colour) {
        this.playerNameRequest = playerNameRequest;
        SocketDto joinLobbyRequest = new SocketDto(SocketMessages.Commands.Lobby.JOIN, new PlayerDto(playerNameRequest, colour));
        clientConnection.sendData(joinLobbyRequest);
    }

    @Override
    public void placeBet(int indexOfBet, int value) {
        CoinDto coinDto = new CoinDto(value, player.getName(), player.getColour());
        BetDto betDto = new BetDto(indexOfBet, coinDto);
        clientConnection.sendData(new SocketDto(SocketMessages.Commands.Betting.BET_ON_CELL, betDto));
    }

    @Override
    public List<Coin> getCoins() {
        return player.getCoins();
    }

}
