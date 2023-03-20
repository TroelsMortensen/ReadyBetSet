package com.pastimegames.readysetbet.javafxclient.socketclient.ViewModel;

import com.pastimegames.readysetbet.javafxclient.socketclient.Core.ViewHandler;
import com.pastimegames.readysetbet.javafxclient.socketclient.Model.Model;
import javafx.application.Platform;

import java.beans.PropertyChangeEvent;

public class JoinLobbyViewModel {

    private Model model;
    private ViewHandler viewHandler;
    private ViewModelPlayer player;

    public JoinLobbyViewModel(Model model) {
        this.model = model;
        this.viewHandler = viewHandler;
        model.addPropertyChangeListener("LOBBY_CLOSED", this::onLobbyClosed);
        player = new ViewModelPlayer();
    }

    private void onLobbyClosed(PropertyChangeEvent propertyChangeEvent) {
        Platform.runLater(()->viewHandler.openRaceView());
    }

    public ViewModelPlayer getPlayer()
    {
        return player;
    }

    public void join() {
        model.joinLobby(player);
    }

    public void setViewHandler(ViewHandler viewHandler)
    {
        this.viewHandler = viewHandler;
    }
}
