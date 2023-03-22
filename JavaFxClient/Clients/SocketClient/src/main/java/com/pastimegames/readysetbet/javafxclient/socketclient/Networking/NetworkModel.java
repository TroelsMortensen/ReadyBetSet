package com.pastimegames.readysetbet.javafxclient.socketclient.Networking;

import com.pastimegames.readysetbet.javafxclient.socketclient.Model.Model;
import com.pastimegames.readysetbet.javafxclient.socketclient.ViewModel.ModelRepresentations.CoinRepresentation;
import com.pastimegames.readysetbet.javafxclient.socketclient.ViewModel.ModelRepresentations.PlayerRepresentation;
import com.pastimegames.shared.datatransferobjects.BetDto;
import com.pastimegames.shared.datatransferobjects.CoinDto;
import com.pastimegames.shared.datatransferobjects.PlayerDto;
import com.pastimegames.shared.datatransferobjects.socketmessages.SocketDto;
import com.pastimegames.shared.datatransferobjects.socketmessages.SocketMessages;

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
    public void joinLobby(PlayerRepresentation player) {
        SocketDto joinLobbyRequest = new SocketDto(SocketMessages.Events.Lobby.PLAYER_JOINED, new PlayerDto(player.getName(), player.getColour()));
        clientConnection.sendData(joinLobbyRequest);
    }

    @Override
    public void placeBet(int indexOfBet, CoinRepresentation coinRepresentation) {
        CoinDto coinDto = new CoinDto(coinRepresentation.getValue(), coinRepresentation.getPlayerName(), coinRepresentation.getColour());
        BetDto betDto = new BetDto(indexOfBet, coinDto);
        clientConnection.sendData(new SocketDto(SocketMessages.Commands.Betting.BET_ON_CELL, betDto));
    }


}
