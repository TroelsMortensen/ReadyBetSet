package com.pastimegames.readysetbet.javafxclient.socketclient.ViewModel.ModelRepresentations;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class CoinRepresentation {

    private final int value;
    private final BooleanProperty isUsed;
    private final int ID;

    public CoinRepresentation(int value, int id) {
        this.value = value;
        ID = id;
        this.isUsed = new SimpleBooleanProperty();
        this.isUsed.set(false);
    }

    public int getValue() {
        return value;
    }

    public BooleanProperty getIsUsedProperty() {
        return isUsed;
    }

    public int getID() {
        return ID;
    }
}
