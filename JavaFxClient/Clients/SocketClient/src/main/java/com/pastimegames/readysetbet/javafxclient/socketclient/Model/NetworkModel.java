package com.pastimegames.readysetbet.javafxclient.socketclient.Model;

import com.pastimegames.readysetbet.core.domain.events.PlayerJoinedLobby;
import com.pastimegames.readysetbet.javafxclient.socketclient.Networking.ClientConnection;
import com.pastimegames.readysetbet.javafxclient.socketclient.ViewModel.ViewModelPlayer;
import com.pastimegames.shared.datatransferobjects.socketmessages.SocketDto;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.net.Socket;

public class NetworkModel implements Model{

    private ClientConnection clientConnection;
    private PropertyChangeSupport propertyChangeSupport;

    public NetworkModel() throws IOException {
        propertyChangeSupport = new PropertyChangeSupport(this);

        Socket socket = new Socket("localhost", 2910);
        clientConnection = new ClientConnection(socket);
        new Thread(clientConnection).start();
    }

    @Override
    public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        if(propertyName == null || propertyName.equals(""))
            addPropertyChangeListener(listener);
        else
            propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    @Override
    public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        if(propertyName == null || propertyName.equals(""))
            removePropertyChangeListener(listener);
        else
            propertyChangeSupport.removePropertyChangeListener(propertyName, listener);
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    @Override
    public void joinLobby(ViewModelPlayer player) {
        clientConnection.joinLobby(player);
    }
}
