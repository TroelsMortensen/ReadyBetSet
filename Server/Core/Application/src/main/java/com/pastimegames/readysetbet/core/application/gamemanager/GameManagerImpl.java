package com.pastimegames.readysetbet.core.application.gamemanager;

import com.pastimegames.readysetbet.core.domain.domainservices.DiceRoller;
import com.pastimegames.readysetbet.core.domain.entities.lobby.Lobby;
import com.pastimegames.readysetbet.core.domain.entities.lobby.Player;
import com.pastimegames.readysetbet.core.domain.entities.race.Race;
import com.pastimegames.readysetbet.core.domain.entities.lobby.RaceOptions;
import com.pastimegames.readysetbet.core.domain.eventpublisher.DomainEventPublisher;
import com.pastimegames.readysetbet.core.domain.events.NewRaceReady;
import com.pastimegames.readysetbet.core.domain.exceptions.DomainLogicException;
import com.pastimegames.shared.datatransferobjects.PlayerDto;

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

    private int raceNumber = 1;

    public GameManagerImpl(DiceRoller diceRoller) {
        this.diceRoller = diceRoller;
        currentGameState = GameState.IN_LOBBY;
        lobby = new Lobby();
    }

    @Override
    public void joinPlayer(PlayerDto playerDto) {
        if(currentGameState != GameState.IN_LOBBY){
            throw new DomainLogicException("Cannot join a game, when it is not in lobby");
        }

        System.out.println("Player joined");
        Player player = new Player(playerDto.playerName(), playerDto.colorCode());

        synchronized (lobby) {
            lobby.join(player);
        }
    }

    @Override
    public void prepareForRacing(RaceOptions options) {
        this.options = options;
        if(currentGameState != GameState.IN_LOBBY){
            throw new DomainLogicException("Cannot initialize a race outside of the lobby");
        }

        setupRace();
    }

    @Override
    public void startRace() {
        if(currentGameState != GameState.RACE_READY){
            throw new DomainLogicException("Cannot start er race, before it is ready");
        }
        currentGameState = GameState.RACE_IN_PROGRESS;
        RaceRunner raceRunner = new RaceRunner(options);
        raceRunner.run(race, diceRoller);
    }

    @Override
    public void removePlayerFromLobby(String playerName) {
        synchronized (lobby) {
            lobby.leave(playerName);
        }
    }

    @Override
    public void displayResults() {
        // TODO what to do here? Something with BookMaker

    }

    @Override
    public void nextRace() {
        if(options.numberOfRaces() >= raceNumber){
            throw new DomainLogicException("You cannot start more races than initially planned");
        }
        raceNumber++;
        setupRace();
    }

    private void setupRace() {
        System.out.println("Race initialized");
        race = new Race();
        race.initializeRace();
        currentGameState = GameState.RACE_READY;
        DomainEventPublisher.instance().publish(new NewRaceReady(raceNumber));

    }

}
