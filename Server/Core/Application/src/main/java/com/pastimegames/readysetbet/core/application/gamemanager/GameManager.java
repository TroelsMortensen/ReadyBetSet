package com.pastimegames.readysetbet.core.application.gamemanager;

import com.pastimegames.shared.datatransferobjects.PlayerDto;

public interface GameManager {
    void joinPlayer(PlayerDto playerdto);

    void initializeRace();
}
