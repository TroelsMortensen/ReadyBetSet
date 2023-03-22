package com.pastimegames.readysetbet.core.domain.valueobjects;

import com.pastimegames.readysetbet.core.domain.entities.race.HorseColor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RaceResult {
    public record HorseData(String name, HorseColor color, int finalPosition) {}

    private final Map<String, HorseData> horses;

    private final int place;
    private final int show;

    public RaceResult(Map<String, HorseData> horses) {
        this.horses = horses;
        List<HorseData> list = new ArrayList<>(horses.values().stream().toList());
        list.sort((o1, o2) -> o2.finalPosition - o1.finalPosition);
        if (list.get(0).finalPosition != 15) {
            throw new RuntimeException("Failure");
        }
        place = list.get(1).finalPosition;
        list.removeIf(horseData -> horseData.finalPosition >= place);
        show = list.get(0).finalPosition;
    }

    public Map<String, HorseData> horses() {
        return horses;
    }

    public boolean isWIN(String horseName) {
        return horses.get(horseName).finalPosition == 15;
    }

    public boolean isPLACE(String horseName) {
        return horses.get(horseName).finalPosition >= place;
    }

    public boolean isSHOW(String horseName) {
        return horses.get(horseName).finalPosition >= show;
    }
}
