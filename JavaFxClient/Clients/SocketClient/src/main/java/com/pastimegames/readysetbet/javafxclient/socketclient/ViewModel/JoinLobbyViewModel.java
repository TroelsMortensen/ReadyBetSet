package com.pastimegames.readysetbet.javafxclient.socketclient.ViewModel;

import com.pastimegames.readysetbet.javafxclient.socketclient.Core.ViewHandler;
import com.pastimegames.readysetbet.javafxclient.socketclient.Model.Model;
import com.pastimegames.readysetbet.javafxclient.socketclient.ViewModel.ModelRepresentations.PlayerRepresentation;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.beans.PropertyChangeEvent;

public class JoinLobbyViewModel {

    private Model model;
    private ViewHandler viewHandler;
    private PlayerRepresentation player;
    private StringProperty name;
    private StringProperty colour;

    public JoinLobbyViewModel(Model model, PlayerRepresentation player) {
        this.model = model;
        this.player = player;
        name = new SimpleStringProperty();
        colour = new SimpleStringProperty();
        player.nameProperty().bindBidirectional(name);
        player.colourProperty().bindBidirectional(colour);
        model.addPropertyChangeListener("LOBBY_CLOSED", this::onLobbyClosed);
    }

    private void onLobbyClosed(PropertyChangeEvent propertyChangeEvent) {
        Platform.runLater(()->viewHandler.openRaceView());
    }

    public void join() {
        model.joinLobby(player);
    }

    public void setViewHandler(ViewHandler viewHandler)
    {
        this.viewHandler = viewHandler;
    }

    public StringProperty getColour()
    {
        return colour;
    }

    public StringProperty getName()
    {
        return name;
    }

}
