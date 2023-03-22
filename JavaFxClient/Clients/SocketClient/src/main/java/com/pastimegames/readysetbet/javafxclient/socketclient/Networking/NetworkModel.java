package com.pastimegames.readysetbet.javafxclient.socketclient.Networking;

import com.pastimegames.readysetbet.javafxclient.socketclient.Model.Bet;
import com.pastimegames.readysetbet.javafxclient.socketclient.Model.Model;
import com.pastimegames.readysetbet.javafxclient.socketclient.ViewModel.ViewModelPlayer;
import com.pastimegames.shared.datatransferobjects.BetDto;
import com.pastimegames.shared.datatransferobjects.CoinDto;
import com.pastimegames.shared.datatransferobjects.PlayerDto;
import com.pastimegames.shared.datatransferobjects.socketmessages.SocketDto;

import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.Socket;

public class NetworkModel implements Model {

    private ClientConnection clientConnection;

    public NetworkModel() {

        Socket socket = null;
        try {
            socket = new Socket("localhost", 2910);
            clientConnection = new ClientConnection(socket);
            new Thread(clientConnection).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        if(propertyName == null || propertyName.equals(""))
            addPropertyChangeListener(listener);
        else
            clientConnection.addPropertyChangeListener(propertyName, listener);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        clientConnection.addPropertyChangeListener(listener);
    }

    @Override
    public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        if(propertyName == null || propertyName.equals(""))
            removePropertyChangeListener(listener);
        else
            clientConnection.removePropertyChangeListener(propertyName, listener);
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        clientConnection.removePropertyChangeListener(listener);
    }

    @Override
    public void joinLobby(ViewModelPlayer player) {
        SocketDto joinLobbyRequest = new SocketDto("Lobby/join", new PlayerDto(player.getName(), player.getColour()));
        clientConnection.sendData(joinLobbyRequest);
    }

    @Override
    public void placeBet(Bet bet) {
        CoinDto coinDto = new CoinDto(bet.bettingCoin().value(), bet.bettingCoin().playerName(), bet.bettingCoin().colour());
        BetDto betDto = new BetDto(bet.betPosition(), coinDto);
        clientConnection.sendData(new SocketDto("Betting/place", betDto));
    }


}
