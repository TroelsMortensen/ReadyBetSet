package com.pastimegames.readysetbet.javafxclient.socketclient.ViewModel;

import com.pastimegames.readysetbet.javafxclient.socketclient.Model.Model;
import com.pastimegames.readysetbet.javafxclient.socketclient.Model.PropertyChangeSubject;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.function.Consumer;

public class JoinLobbyViewModel implements PropertyChangeSubject {

    private final Model model;

    private final StringProperty name;
    private final StringProperty colour;
    private final StringProperty systemResponse;
    private final BooleanProperty joinRequestAccepted;

    private PropertyChangeSupport support;

    public JoinLobbyViewModel(Model model) {
        this.model = model;
        support = new PropertyChangeSupport(this);

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
        support.firePropertyChange("LOBBY_CLOSED", null, null);
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

    /*
    PropertyChangeSubject interface implementation
     */
    @Override
    public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        if (propertyName == null || propertyName.equals(""))
            addPropertyChangeListener(listener);
        else
            support.addPropertyChangeListener(propertyName, listener);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    @Override
    public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        if (propertyName == null || propertyName.equals(""))
            removePropertyChangeListener(listener);
        else
            support.removePropertyChangeListener(propertyName, listener);
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }
}
