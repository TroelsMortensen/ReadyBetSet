package com.pastimegames.readysetbet.core.domain.valueobjects;

import com.pastimegames.readysetbet.core.domain.entities.race.HorseColor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RaceResult {
    public record HorseData(String name, HorseColor color, int finalPosition) {
    }

    private final Map<String, HorseData> horses;

    private final int placeIndex;
    private final int showIndex;

    public RaceResult(Map<String, HorseData> horses) {
        this.horses = horses;
        List<HorseData> list = new ArrayList<>(horses.values().stream().toList());
        list.sort((o1, o2) -> o2.finalPosition - o1.finalPosition);
        if (list.get(0).finalPosition != 15) {
            throw new RuntimeException("Failure");
        }
        placeIndex = list.get(1).finalPosition;
        list.removeIf(horseData -> horseData.finalPosition >= placeIndex);
        showIndex = list.get(0).finalPosition;
    }

    public Map<String, HorseData> horses() {
        return horses;
    }

    public boolean isWIN(String horseName) {
        return horses.get(horseName).finalPosition == 15;
    }

    public boolean isPLACE(String horseName) {
        return horses.get(horseName).finalPosition >= placeIndex;
    }

    public boolean isSHOW(String horseName) {
        return horses.get(horseName).finalPosition >= showIndex;
    }

    public String getNameOfWinnerHorse() {
        HorseData winnerHorse = horses.values().stream().filter(horseData -> isWIN(horseData.name())).findFirst().get();
        return winnerHorse.name();
    }

    public List<String> getNamesOfPlaceHorses() {
        List<String> collect = horses.values().stream().filter(horseData -> isPLACE(horseData.name())).map(HorseData::name).toList();
        return collect;
    }

    public List<String> getNamesOfShowHorses() {
        List<String> collect = horses.values().stream().filter(horseData -> isSHOW(horseData.name())).map(HorseData::name).toList();
        return collect;
    }
}
