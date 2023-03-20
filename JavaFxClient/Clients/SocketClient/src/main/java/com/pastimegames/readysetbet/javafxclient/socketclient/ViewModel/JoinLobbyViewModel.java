package com.pastimegames.readysetbet.javafxclient.socketclient.ViewModel;

import com.pastimegames.readysetbet.javafxclient.socketclient.Model.Model;

public class JoinLobbyViewModel {

    private Model model;
    private ViewModelPlayer player;

    public JoinLobbyViewModel(Model model) {
        this.model = model;
        player = new ViewModelPlayer();
    }

    public ViewModelPlayer getPlayer()
    {
        return player;
    }

    public void join() {
        model.joinLobby(player);
    }
}
