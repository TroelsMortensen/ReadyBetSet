package com.pastimegames.readysetbet.javafxclient.socketclient.ViewModel;

import com.pastimegames.readysetbet.javafxclient.socketclient.Model.Model;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.beans.PropertyChangeEvent;
import java.util.function.Consumer;

public class JoinLobbyViewModel {

    private final Model model;

    private final StringProperty name;
    private final StringProperty colour;
    private final StringProperty systemResponse;
    private final BooleanProperty joinRequestAccepted;

    private Consumer onLobbyClosed; //TODO: Could use PropertyChangeSubject instead, but is overkill. Is there another way?

    public JoinLobbyViewModel(Model model) {
        this.model = model;
        name = new SimpleStringProperty();
        colour = new SimpleStringProperty();
        systemResponse = new SimpleStringProperty();
        joinRequestAccepted = new SimpleBooleanProperty(false);
        initializeListeners();
    }

    private void initializeListeners() {
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
        onLobbyClosed.accept(null);
    }

    public void join() {
        model.joinLobby(name.getValue(), colour.getValue());
    }

    public StringProperty getColourProperty() {
        return colour;
    }

    public StringProperty getNameProperty() {
        return name;
    }

    public StringProperty systemResponseProperty() {
        return systemResponse;
    }

    public BooleanProperty joinRequestAcceptedProperty() {
        return joinRequestAccepted;
    }

    public void setOnLobbyClosed(Consumer onLobbyClosed) {
        this.onLobbyClosed = onLobbyClosed;
    }
}
