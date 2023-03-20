package com.pastimegames.readysetbet.javafxclient.socketclient.ViewModel;

import com.pastimegames.readysetbet.javafxclient.socketclient.Model.Model;

import java.beans.PropertyChangeEvent;

public class JoinLobbyViewModel {

    private Model model;
    private ViewModelPlayer player;

    public JoinLobbyViewModel(Model model) {
        this.model = model;
        model.addPropertyChangeListener("LOBBY_CLOSED", this::onLobbyClosed);
        player = new ViewModelPlayer();
    }

    private void onLobbyClosed(PropertyChangeEvent propertyChangeEvent) {
        //TODO: change view
    }

    public ViewModelPlayer getPlayer()
    {
        return player;
    }

    public void join() {
        model.joinLobby(player);
    }
}
