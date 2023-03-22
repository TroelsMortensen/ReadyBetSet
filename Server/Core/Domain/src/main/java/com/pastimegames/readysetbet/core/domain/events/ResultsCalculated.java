package com.pastimegames.readysetbet.core.domain.events;

import java.util.Map;

public record ResultsCalculated(Map<String, Integer> playerSaldos) {
    public static String type(){
        return ResultsCalculated.class.getName();
    }
}
