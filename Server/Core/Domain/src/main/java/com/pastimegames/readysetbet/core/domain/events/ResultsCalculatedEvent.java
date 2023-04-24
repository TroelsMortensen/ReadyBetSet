package com.pastimegames.readysetbet.core.domain.events;

import com.pastimegames.readysetbet.core.domain.valueobjects.PlayerBalances;

public record ResultsCalculatedEvent(PlayerBalances playerBalances) {
    public static String type(){
        return ResultsCalculatedEvent.class.getName();
    }
}
