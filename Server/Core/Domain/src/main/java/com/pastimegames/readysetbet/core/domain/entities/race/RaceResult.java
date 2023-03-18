package com.pastimegames.readysetbet.core.domain.entities.race;

import java.util.List;

public record RaceResult(List<Horse> horses) {
    public record Horse(String name, HorseColor color, int finalPosition){

    }
}
