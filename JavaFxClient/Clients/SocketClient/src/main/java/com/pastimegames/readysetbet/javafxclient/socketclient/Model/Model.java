package com.pastimegames.readysetbet.javafxclient.socketclient.Model;

import com.pastimegames.readysetbet.javafxclient.socketclient.ViewModel.ModelRepresentations.CoinRepresentation;
import com.pastimegames.readysetbet.javafxclient.socketclient.ViewModel.ModelRepresentations.PlayerRepresentation;

public interface Model extends PropertyChangeSubject{
    void joinLobby(PlayerRepresentation player);

    void placeBet(int indexOfBet, CoinRepresentation coin);
}
