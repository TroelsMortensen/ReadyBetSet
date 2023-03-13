package com.pastimegames.readysetbet.core.application.gamemanager;

import com.pastimegames.readysetbet.core.domain.entities.lobby.RaceOptions;
import com.pastimegames.readysetbet.shared.viewmodels.PlayerVM;

public interface GameManager {
    void joinPlayer(PlayerVM playerdto);
    void prepareForRacing(RaceOptions options);

    void startRace();

    void removePlayerFromLobby(String playerName);

    void displayResults();

    void nextRace();
}

