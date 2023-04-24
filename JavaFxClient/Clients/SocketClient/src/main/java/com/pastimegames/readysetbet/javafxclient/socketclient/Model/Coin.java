package com.pastimegames.readysetbet.javafxclient.socketclient.Model;

public class Coin {

    private final int value;
    private boolean isUsed;
    private final int ID; //TODO: Ensure uniqueness

    public Coin(int value, boolean isUsed, int id) {
        this.value = value;
        this.isUsed = isUsed;
        ID = id;
    }

    public int getValue() {
        return value;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }

    public int getID() {
        return ID;
    }
}
