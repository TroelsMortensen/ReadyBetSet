package com.pastimegames.readysetbet.javafxclient.socketclient.Networking;

import com.pastimegames.readysetbet.javafxclient.socketclient.Events.BetPlacedEvent;
import com.pastimegames.readysetbet.javafxclient.socketclient.Events.HorseMovedEvent;
import com.pastimegames.readysetbet.javafxclient.socketclient.Events.PlayerJoinedEvent;
import com.pastimegames.readysetbet.javafxclient.socketclient.Model.PropertyChangeSubject;
import com.pastimegames.shared.datatransferobjects.BetPlacedOnCellDto;
import com.pastimegames.shared.datatransferobjects.HorseMovedDto;
import com.pastimegames.shared.datatransferobjects.PlayerJoinedLobbyDto;
import com.pastimegames.shared.datatransferobjects.socketmessages.SocketDto;
import com.pastimegames.shared.datatransferobjects.socketmessages.SocketMessages;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.*;
import java.net.Socket;

public class ClientConnection implements Runnable, PropertyChangeSubject {


    private ObjectInputStream inFromServer;
    private ObjectOutputStream outToServer;
    private PropertyChangeSupport propertyChangeSupport;

    public ClientConnection(Socket serverSocket) {
        propertyChangeSupport = new PropertyChangeSupport(this);
        try {
            outToServer = new ObjectOutputStream(serverSocket.getOutputStream());
            inFromServer = new ObjectInputStream(serverSocket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
            try {
                while(true) {
                    SocketDto response = (SocketDto) inFromServer.readObject();
                    System.out.println("Received from server: " + response.content());
                    switch (response.commandType())
                    {
                        case "gotoraceview" : case SocketMessages.Events.Lobby.LOBBY_FINALIZED:
                            gotoraceview();
                            break;
                        case SocketMessages.Events.Race.HORSE_MOVED:
                            horsemoved((HorseMovedDto) response.content());
                            break;
                        case SocketMessages.Events.Betting.BET_PLACED:
                            betPlaced((BetPlacedOnCellDto) response.content());
                            break;
                        case SocketMessages.Events.Lobby.PLAYER_JOINED:
                            playerJoined((PlayerJoinedLobbyDto) response.content());
                            break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
    }

    private void playerJoined(PlayerJoinedLobbyDto content) {
        PlayerJoinedEvent playerJoinedLobbyEvent = new PlayerJoinedEvent(content.name(), content.color());
        propertyChangeSupport.firePropertyChange("PLAYER_JOINED", null, playerJoinedLobbyEvent);
    }

    private void betPlaced(BetPlacedOnCellDto betPlaced)
    {
        BetPlacedEvent betPlacedEvent = new BetPlacedEvent(betPlaced.index(), betPlaced.coinValue(), betPlaced.color(), betPlaced.owningPlayer());
        propertyChangeSupport.firePropertyChange("BET_PLACED", null, betPlacedEvent);
    }

    private void horsemoved(HorseMovedDto horseMovedDto) {
        HorseMovedEvent horseMovedEvent = new HorseMovedEvent(horseMovedDto.horseName(), horseMovedDto.position());
        propertyChangeSupport.firePropertyChange("HORSE_MOVED",null, horseMovedEvent);
    }

    public void sendData(SocketDto data)
    {
        try {
            outToServer.writeObject(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void gotoraceview() {
        propertyChangeSupport.firePropertyChange("LOBBY_CLOSED", null, null);
    }

    public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        if(propertyName == null || propertyName.equals(""))
            addPropertyChangeListener(listener);
        else
            propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        if(propertyName == null || propertyName.equals(""))
            removePropertyChangeListener(listener);
        else
            propertyChangeSupport.removePropertyChangeListener(propertyName, listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

}
