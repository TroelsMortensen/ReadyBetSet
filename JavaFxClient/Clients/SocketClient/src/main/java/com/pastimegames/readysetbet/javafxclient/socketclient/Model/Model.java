package com.pastimegames.readysetbet.javafxclient.socketclient.Model;

import com.pastimegames.readysetbet.javafxclient.socketclient.ViewModel.ModelRepresentations.CoinRepresentation;

import java.util.List;

public interface Model extends PropertyChangeSubject{
    void joinLobby(String playerNameRequest, String colour);

    void placeBet(int indexOfBet, int value);

    List<Coin> getCoins();
}
