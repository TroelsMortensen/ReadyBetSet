package com.pastimegames.readysetbet.core.application.gamemanager;

import com.pastimegames.readysetbet.core.domain.entities.lobby.RaceOptions;
import com.pastimegames.shared.datatransferobjects.BetDto;
import com.pastimegames.shared.datatransferobjects.PlayerDto;

public interface GameManager {
    void joinLobby(PlayerDto playerdto);

    /**
     * Use when lobby stuff is done, and you want to start the races
     * @param options
     */
    void finalizeLobby(RaceOptions options);

    /**
     * Start a race
     */
    void startRace();

    void leaveLobby(String playerName);

    void placeBet(BetDto betDto);

    /**
     * Use when a race is finished, and you want an overview of the results.
     */
    void displayResults();

    /**
     * After viewing the race results, use this method to start the next race
     */
    void nextRace();
}

