package com.pastimegames.readysetbet.core.application.gamemanager;

import com.pastimegames.readysetbet.core.domain.domainservices.DiceRoller;
import com.pastimegames.readysetbet.core.domain.entities.betting.BookMaker;
import com.pastimegames.readysetbet.core.domain.entities.betting.Coin;
import com.pastimegames.readysetbet.core.domain.entities.lobby.Lobby;
import com.pastimegames.readysetbet.core.domain.entities.lobby.Player;
import com.pastimegames.readysetbet.core.domain.entities.lobby.RaceOptions;
import com.pastimegames.readysetbet.core.domain.entities.race.Race;
import com.pastimegames.readysetbet.core.domain.eventpublisher.DomainEventListener;
import com.pastimegames.readysetbet.core.domain.eventpublisher.DomainEventPublisher;
import com.pastimegames.readysetbet.core.domain.events.NextRaceReadyEvent;
import com.pastimegames.readysetbet.core.domain.events.LobbyFinalizedEvent;
import com.pastimegames.readysetbet.core.domain.events.RaceFinishedEvent;
import com.pastimegames.readysetbet.core.domain.events.ResultsCalculatedEvent;
import com.pastimegames.readysetbet.core.domain.exceptions.GameLogicException;
import com.pastimegames.readysetbet.core.domain.valueobjects.PlayerBalances;
import com.pastimegames.readysetbet.core.domain.valueobjects.RaceResult;
import com.pastimegames.shared.datatransferobjects.BetDto;
import com.pastimegames.shared.datatransferobjects.CoinDto;
import com.pastimegames.shared.datatransferobjects.PlayerDto;

public class GameManagerImpl implements GameManager {


    private BookMaker bookMaker;

//    private enum GameState {
//        IN_LOBBY,
//        RACE_READY,
//        RACE_IN_PROGRESS,
//        RACE_FINISHED,
//        IN_RESULTS;
//    }
    private final DiceRoller diceRoller;

    private final Lobby lobby;
    private GameState gameState;
    private Race race;
    private RaceOptions options;

    private int raceNumber = 1;

    public GameManagerImpl(DiceRoller diceRoller) {
        this.diceRoller = diceRoller;
        gameState = new GameState();
        gameState.updateState(GameState.State.IN_LOBBY);
        lobby = new Lobby();

        DomainEventPublisher.instance().subscribe(RaceFinishedEvent.type(), (DomainEventListener<RaceFinishedEvent>) raceFinished -> {
            gameState.updateState(GameState.State.RACE_FINISHED);
        });
    }

    @Override
    public void joinLobby(PlayerDto playerDto) {
        if(gameState.currentGameState() != GameState.State.IN_LOBBY){
            throw new GameLogicException("Cannot join a game, when it is not in lobby");
        }

        System.out.println("Player joined");
        Player player = new Player(playerDto.playerName(), playerDto.colorCode());

        synchronized (lobby) {
            lobby.join(player);
        }
    }

    @Override
    public void leaveLobby(String playerName) {
        synchronized (lobby) {
            lobby.leave(playerName);
        }
    }

    @Override
    public void finalizeLobby(RaceOptions options) {
        this.options = options;
        if(gameState.currentGameState() != GameState.State.IN_LOBBY){
            throw new GameLogicException("Cannot initialize a race outside of the lobby");
        }

        setupRace();
        DomainEventPublisher.instance().publish(new LobbyFinalizedEvent(options));
    }

    @Override
    public void startRace() {
        if(gameState.currentGameState() != GameState.State.RACE_READY){
            throw new GameLogicException("Cannot start er race, before it is ready");
        }
        gameState.updateState(GameState.State.RACE_IN_PROGRESS);
        RaceRunner raceRunner = new RaceRunner(options);
        raceRunner.run(race, diceRoller);
    }

    @Override
    public void placeBet(BetDto betDto) {
        if(gameState.currentGameState() != GameState.State.RACE_IN_PROGRESS) {
            throw new GameLogicException("Cannot bet when race is not in progress.");
        }
        CoinDto coin = betDto.coin();
        synchronized (bookMaker) {
            Coin coinToPlace = new Coin(coin.value(), coin.playerName(), coin.color());
            bookMaker.betOnCell(betDto.betPosition(), coinToPlace);
        }
    }

    @Override
    public void displayResults() {
        if(gameState.currentGameState() != GameState.State.RACE_FINISHED){
            throw new GameLogicException("Cannot display results if race is not in finished state");
        }
        RaceResult raceResult = race.getRaceResult();
        bookMaker.deliverWinnings(raceResult, lobby);
        bookMaker.deliverPenalties(raceResult, lobby);
        PlayerBalances saldos = lobby.getPlayerBalances();
        DomainEventPublisher.instance().publish(new ResultsCalculatedEvent(saldos, raceResult));
    }

    @Override
    public void nextRace() {
        if(options.numberOfRaces() >= raceNumber){
            throw new GameLogicException("You cannot start more races than initially planned");
        }
        raceNumber++;
        setupRace();
        DomainEventPublisher.instance().publish(new NextRaceReadyEvent(raceNumber));
    }

    private void setupRace() {
        race = new Race();
        race.initializeRace();
        gameState.updateState(GameState.State.RACE_READY);
        bookMaker = new BookMaker();
        // TODO reset noget betting mht coins?
    }

}
