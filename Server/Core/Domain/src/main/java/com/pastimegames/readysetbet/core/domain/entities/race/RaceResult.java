package com.pastimegames.readysetbet.core.domain.entities.race;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public record RaceResult(Map<String, HorseData> horses) {
    public record HorseData(String name, HorseColor color, int finalPosition) {

    }
    // TODO I need unit tests for these calculations
    public boolean isWIN(String horseName) {
        return horses.get(horseName).finalPosition == 15;
    }

    public boolean isPLACE(String horseName) {
        return horses.get(horseName).finalPosition >= findSecondPlaceIndex();
    }

    public boolean isSHOW(String horseName) {
        return horses.get(horseName).finalPosition >= findThirdPlaceIndex();
    }

    private int findSecondPlaceIndex() {
        List<HorseData> list = new ArrayList<>(horses.values().stream().toList());
        list.sort((o1, o2) -> Integer.compare(o2.finalPosition, o1.finalPosition));
        return list.get(1).finalPosition;
    }

    private int findThirdPlaceIndex() {
        List<HorseData> list = new ArrayList<>(horses.values().stream().toList());
        list.removeIf(horseData -> horseData.finalPosition == 15);
        list.removeIf(horseData -> horseData.finalPosition == findSecondPlaceIndex());
        list.sort((o1, o2) -> o2.finalPosition - o1.finalPosition);
        return list.get(0).finalPosition;
    }

}
