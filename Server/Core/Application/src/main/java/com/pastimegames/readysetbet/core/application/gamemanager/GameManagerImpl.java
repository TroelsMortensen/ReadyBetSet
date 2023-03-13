package com.pastimegames.readysetbet.core.application.gamemanager;

import com.pastimegames.readysetbet.core.domain.domainservices.DiceRoller;
import com.pastimegames.readysetbet.core.domain.entities.lobby.Lobby;
import com.pastimegames.readysetbet.core.domain.entities.lobby.Player;
import com.pastimegames.readysetbet.core.domain.entities.race.Race;
import com.pastimegames.readysetbet.core.domain.entities.lobby.RaceOptions;
import com.pastimegames.readysetbet.shared.viewmodels.PlayerVM;

public class GameManagerImpl implements GameManager {


    private enum GameState {
        IN_LOBBY,
        RACE_READY,
        RACE_IN_PROGRESS,
        IN_RESULTS;
    }
    private final DiceRoller diceRoller;

    private final Lobby lobby;
    private GameState currentGameState;
    private Race race;
    private RaceOptions options;

    public GameManagerImpl(DiceRoller diceRoller) {
        this.diceRoller = diceRoller;
        currentGameState = GameState.IN_LOBBY;
        lobby = new Lobby();
    }

    @Override
    public void joinPlayer(PlayerVM playerdto) {
        if(currentGameState != GameState.IN_LOBBY){
            throw new RuntimeException("Cannot join a game, when it is not in lobby");
        }

        System.out.println("Player joined");
        Player player = new Player(playerdto.playerName(), playerdto.colorCode());

        synchronized (lobby) {
            lobby.join(player);
        }
    }

    @Override
    public void initializeRace(RaceOptions options) {
        this.options = options;
        if(currentGameState != GameState.IN_LOBBY){
            throw new RuntimeException("Cannot initialize a race outside of the lobby");
        }
        System.out.println("Race initialized");
        race = new Race();
        race.initializeRace();
        currentGameState = GameState.RACE_READY;
    }

    @Override
    public void startRace() {
        if(currentGameState != GameState.RACE_READY){
            throw new RuntimeException("Cannot start er race, before it is ready");
        }
        currentGameState = GameState.RACE_IN_PROGRESS;
        RaceRunner raceRunner = new RaceRunner(options);
        raceRunner.run(race, diceRoller);
    }

}
