package com.pastimegames.readysetbet.core.domain.events;

import java.util.Map;

public record ResultsCalculatedEvent(Map<String, Integer> playerSaldos) {
    public static String type(){
        return ResultsCalculatedEvent.class.getName();
    }
}
