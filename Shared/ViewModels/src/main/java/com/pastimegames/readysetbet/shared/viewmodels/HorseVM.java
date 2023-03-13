package com.pastimegames.readysetbet.shared.viewmodels;

public class HorseVM {
    private String name;
    private String color;
    private int position;

    public HorseVM(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public void updatePosition(int newPos){
        position = newPos;
    }

    public int position() {
        return position;
    }

    public String name() {
        return name;
    }
}
