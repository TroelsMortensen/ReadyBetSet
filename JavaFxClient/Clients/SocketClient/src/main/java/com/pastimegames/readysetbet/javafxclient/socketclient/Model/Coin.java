package com.pastimegames.readysetbet.javafxclient.socketclient.Model;

public class Coin {

    private int value;
    private boolean isUsed;
    private int ID; //TODO: Test again without, and if it works, remove

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
