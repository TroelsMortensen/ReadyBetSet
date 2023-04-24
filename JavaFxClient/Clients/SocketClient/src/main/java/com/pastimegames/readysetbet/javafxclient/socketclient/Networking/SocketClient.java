package com.pastimegames.readysetbet.javafxclient.socketclient.Networking;

import com.pastimegames.readysetbet.javafxclient.socketclient.Events.BetPlacedEvent;
import com.pastimegames.readysetbet.javafxclient.socketclient.Events.HorseMovedEvent;
import com.pastimegames.readysetbet.javafxclient.socketclient.Events.PlayerJoinedEvent;
import com.pastimegames.shared.datatransferobjects.*;
import com.pastimegames.shared.datatransferobjects.socketmessages.SocketDto;
import com.pastimegames.shared.datatransferobjects.socketmessages.SocketMessages;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.*;
import java.net.Socket;

public class SocketClient implements Runnable, Client {


    private ObjectInputStream inFromServer;
    private ObjectOutputStream outToServer;
    private final PropertyChangeSupport propertyChangeSupport;

    public SocketClient() {
        propertyChangeSupport = new PropertyChangeSupport(this);
        try {
            Socket serverSocket = new Socket("localhost", 2910);
            outToServer = new ObjectOutputStream(serverSocket.getOutputStream());
            inFromServer = new ObjectInputStream(serverSocket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        new Thread(this).start();
    }


    /*
    Client interface implementation
     */

    @Override
    public void placeBet(int indexOfBet, int value, String playerName, String colour) {
        CoinDto coinDto = new CoinDto(value, playerName, colour);
        BetDto betDto = new BetDto(indexOfBet, coinDto);
        SocketDto request = new SocketDto(SocketMessages.Commands.Betting.BET_ON_CELL, betDto);
        sendData(request);
    }

    @Override
    public void joinLobby(String playerNameRequest, String colour) {
        PlayerDto playerDto = new PlayerDto(playerNameRequest, colour);
        SocketDto request = new SocketDto(SocketMessages.Commands.Lobby.JOIN, playerDto);
        sendData(request);
    }

    /*
    Runnable interface implementation
     */
    @Override
    public void run() {
        try {
            while (true) {
                SocketDto response = (SocketDto) inFromServer.readObject();
                System.out.println("Received from server: " + response.content());
                switch (response.commandType()) {
                    case SocketMessages.Events.Lobby.LOBBY_FINALIZED -> lobbyFinalized();
                    case SocketMessages.Events.Race.HORSE_MOVED -> horsemoved((HorseMovedDto) response.content());
                    case SocketMessages.Events.Betting.BET_PLACED -> betPlaced((BetPlacedOnCellDto) response.content());
                    case SocketMessages.Events.Lobby.PLAYER_JOINED -> playerJoined((PlayerJoinedLobbyDto) response.content());
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /*
    private methods
     */
    private void sendData(SocketDto data) {
        try {
            outToServer.writeObject(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void playerJoined(PlayerJoinedLobbyDto content) {
        PlayerJoinedEvent playerJoinedLobbyEvent = new PlayerJoinedEvent(content.name(), content.color());
        propertyChangeSupport.firePropertyChange("PLAYER_JOINED", null, playerJoinedLobbyEvent);
    }

    private void betPlaced(BetPlacedOnCellDto betPlaced) {
        BetPlacedEvent betPlacedEvent = new BetPlacedEvent(betPlaced.index(), betPlaced.coinValue(), betPlaced.owningPlayer(), betPlaced.color());
        propertyChangeSupport.firePropertyChange("BET_PLACED", null, betPlacedEvent);
    }

    private void horsemoved(HorseMovedDto horseMovedDto) {
        HorseMovedEvent horseMovedEvent = new HorseMovedEvent(horseMovedDto.horseName(), horseMovedDto.position());
        propertyChangeSupport.firePropertyChange("HORSE_MOVED", null, horseMovedEvent);
    }

    private void lobbyFinalized() {
        propertyChangeSupport.firePropertyChange("LOBBY_CLOSED", null, null);
    }

    /*
    PropertyChangeSubject interface implementation
     */
    @Override
    public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        if (propertyName == null || propertyName.equals(""))
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
        if (propertyName == null || propertyName.equals(""))
            removePropertyChangeListener(listener);
        else
            propertyChangeSupport.removePropertyChangeListener(propertyName, listener);
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

}
