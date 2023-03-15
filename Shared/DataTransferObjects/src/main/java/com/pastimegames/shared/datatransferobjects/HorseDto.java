package com.pastimegames.shared.datatransferobjects;

public class HorseDto {
    private String name;
    private String color;
    private int position;

    public HorseDto(String name, String color) {
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
