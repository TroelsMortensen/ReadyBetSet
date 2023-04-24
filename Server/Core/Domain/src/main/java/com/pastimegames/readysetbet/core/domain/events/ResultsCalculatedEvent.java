package com.pastimegames.readysetbet.core.domain.events;

import com.pastimegames.readysetbet.core.domain.valueobjects.PlayerBalances;
import com.pastimegames.readysetbet.core.domain.valueobjects.RaceResult;

public record ResultsCalculatedEvent(PlayerBalances playerBalances, RaceResult raceResult) {
    public static String type(){
        return ResultsCalculatedEvent.class.getName();
    }
}
