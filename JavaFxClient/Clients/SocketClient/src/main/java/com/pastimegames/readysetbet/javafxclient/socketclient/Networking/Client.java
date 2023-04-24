package com.pastimegames.readysetbet.javafxclient.socketclient.Networking;

import com.pastimegames.readysetbet.javafxclient.socketclient.Model.PropertyChangeSubject;

public interface Client extends PropertyChangeSubject {

    void placeBet(int indexOfBet, int value, String playerName, String colour);
    void joinLobby(String playerNameRequest, String colour);
    void start();

}
