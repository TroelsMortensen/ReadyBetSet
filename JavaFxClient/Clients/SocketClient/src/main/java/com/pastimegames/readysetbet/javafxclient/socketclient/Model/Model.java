package com.pastimegames.readysetbet.javafxclient.socketclient.Model;

import com.pastimegames.readysetbet.javafxclient.socketclient.ViewModel.PlayerRepresentation;

public interface Model extends PropertyChangeSubject{
    void joinLobby(PlayerRepresentation player);

    void placeBet(Bet bet);
}
