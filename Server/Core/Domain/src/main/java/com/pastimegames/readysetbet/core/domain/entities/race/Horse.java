package com.pastimegames.readysetbet.core.domain.entities.race;

import com.pastimegames.readysetbet.core.domain.exceptions.DomainLogicException;

public class Horse {

    private final String name;
    private final HorseColor color;
    private final int bonusMoves;
    private int position;

    public Horse(String name, HorseColor color, int bonusMoves) {
        this.name = name;
        this.color = color;
        this.bonusMoves = bonusMoves;
        position = 0;
        validate();
    }

    private void validate() {
        if(name == null || "".equals(name)) throw new DomainLogicException("Horse cannot be created without name");
        if(color == null) throw new DomainLogicException("Horse must have a color");
    }

    public void move() {
        moveSteps(1);
    }

    public void sprint() {
        moveSteps(1 + bonusMoves);
    }

    private void moveSteps(int numberOfMoveSteps){
        position += numberOfMoveSteps;
        if(position > 15){
            position = 15;
        }
        System.out.println("Horse " + name + " now at " + position);
    }

    public String name(){
        return name;
    }

    public int position() {
        return position;
    }

    @Override
    public String toString() {
        return name;
    }

    public HorseColor color() {
        return color;
    }
}
