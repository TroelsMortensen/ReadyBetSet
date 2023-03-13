package com.pastimegames.readysetbet.core.application.gamemanager;

import com.pastimegames.readysetbet.core.domain.entities.lobby.RaceOptions;
import com.pastimegames.readysetbet.shared.viewmodels.PlayerVM;

public interface GameManager {
    void joinPlayer(PlayerVM playerdto);
    void initializeRace(RaceOptions options);

    void startRace();
}

