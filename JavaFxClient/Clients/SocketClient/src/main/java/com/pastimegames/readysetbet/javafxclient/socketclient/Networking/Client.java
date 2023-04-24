package com.pastimegames.readysetbet.javafxclient.socketclient.Networking;

public interface Client {

    void placeBet(int indexOfBet, int value);
    void joinLobby(String playerNameRequest, String colour);

}
