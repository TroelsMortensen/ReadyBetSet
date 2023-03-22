package com.pastimegames.readysetbet.javafxclient.socketclient.Model;

import com.pastimegames.readysetbet.javafxclient.socketclient.ViewModel.ViewModelPlayer;

public interface Model extends PropertyChangeSubject{
    void joinLobby(ViewModelPlayer player);

    void placeBet(Bet bet);
}
