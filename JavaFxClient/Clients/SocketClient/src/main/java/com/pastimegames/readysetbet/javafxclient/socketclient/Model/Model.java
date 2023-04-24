package com.pastimegames.readysetbet.javafxclient.socketclient.Model;

import java.util.List;

public interface Model extends PropertyChangeSubject{
    void joinLobby(String playerNameRequest, String colour);
    void placeBet(int indexOfBet, int value);
    List<Coin> getCoins();
    //will also contain pickPowerUp and stuff at a later point
}
