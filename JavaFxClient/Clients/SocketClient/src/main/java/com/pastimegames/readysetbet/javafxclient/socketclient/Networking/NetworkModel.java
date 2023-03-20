package com.pastimegames.readysetbet.javafxclient.socketclient.Networking;

import com.pastimegames.readysetbet.core.domain.events.PlayerJoinedLobby;
import com.pastimegames.readysetbet.javafxclient.socketclient.Model.Model;
import com.pastimegames.readysetbet.javafxclient.socketclient.Networking.ClientConnection;
import com.pastimegames.readysetbet.javafxclient.socketclient.ViewModel.ViewModelPlayer;
import com.pastimegames.shared.datatransferobjects.PlayerDto;
import com.pastimegames.shared.datatransferobjects.socketmessages.SocketDto;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.net.Socket;

public class NetworkModel implements Model {

    private ClientConnection clientConnection;

    public NetworkModel() throws IOException {

        Socket socket = new Socket("localhost", 2910);
        clientConnection = new ClientConnection(socket);
        new Thread(clientConnection).start();
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
        clientConnection.sendSocketDto(joinLobbyRequest);
    }


}
