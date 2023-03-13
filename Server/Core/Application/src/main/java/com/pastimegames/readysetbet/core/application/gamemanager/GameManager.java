package com.pastimegames.readysetbet.core.application.gamemanager;

import com.pastimegames.readysetbet.core.domain.entities.lobby.RaceOptions;
import com.pastimegames.readysetbet.shared.viewmodels.PlayerVM;

public interface GameManager {
    void joinPlayer(PlayerVM playerdto);

    /**
     * Use when lobby stuff is done, and you want to start the races
     * @param options
     */
    void prepareForRacing(RaceOptions options);

    /**
     * Start a race
     */
    void startRace();

    void removePlayerFromLobby(String playerName);


    /**
     * Use when a race is finished, and you want an overview of the results.
     */
    void displayResults();

    /**
     * After viewing the race results, use this method to start the next race
     */
    void nextRace();
}

