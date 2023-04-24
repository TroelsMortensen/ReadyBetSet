package com.pastimegames.readysetbet.javafxclient.socketclient.ViewModel;

import com.pastimegames.readysetbet.javafxclient.socketclient.Core.ViewHandler;
import com.pastimegames.readysetbet.javafxclient.socketclient.Model.Model;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.beans.PropertyChangeEvent;

public class JoinLobbyViewModel {

    private Model model;
    private ViewHandler viewHandler;
    private StringProperty name;
    private StringProperty colour;
    private StringProperty systemResponse;


    private BooleanProperty joinRequestAccepted;

    public JoinLobbyViewModel(Model model) {
        this.model = model;

        name = new SimpleStringProperty();
        colour = new SimpleStringProperty();
        systemResponse = new SimpleStringProperty();
        joinRequestAccepted = new SimpleBooleanProperty(false);

        model.addPropertyChangeListener("LOBBY_CLOSED", this::onLobbyClosed);
        model.addPropertyChangeListener("PLAYER_JOIN_REQUEST_ACCEPTED", this::onPlayerJoinRequestAccepted);
    }

    private void onPlayerJoinRequestAccepted(PropertyChangeEvent propertyChangeEvent) {
        Platform.runLater(() ->
                {
                    systemResponse.set("JOIN REQUEST ACCEPTED");
                    joinRequestAccepted.set(true);
                }
        );
    }

    private void onLobbyClosed(PropertyChangeEvent propertyChangeEvent) {
        Platform.runLater(() -> viewHandler.openBettingView());
    }

    public void join() {
        model.joinLobby(name.getValue(), colour.getValue());
    }

    public void setViewHandler(ViewHandler viewHandler) {
        this.viewHandler = viewHandler;
    }

    public StringProperty getColour() {
        return colour;
    }

    public StringProperty getName() {
        return name;
    }

    public StringProperty systemResponseProperty() {
        return systemResponse;
    }

    public BooleanProperty joinRequestAcceptedProperty() {
        return joinRequestAccepted;
    }
}
