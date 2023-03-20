package com.pastimegames.readysetbet.core.application.gamemanager;

import com.pastimegames.readysetbet.core.domain.domainservices.DiceRoller;
import com.pastimegames.readysetbet.core.domain.entities.betting.BookMaker;
import com.pastimegames.readysetbet.core.domain.entities.betting.Coin;
import com.pastimegames.readysetbet.core.domain.entities.lobby.Lobby;
import com.pastimegames.readysetbet.core.domain.entities.lobby.Player;
import com.pastimegames.readysetbet.core.domain.entities.lobby.RaceOptions;
import com.pastimegames.readysetbet.core.domain.entities.race.Race;
import com.pastimegames.readysetbet.core.domain.eventpublisher.DomainEventPublisher;
import com.pastimegames.readysetbet.core.domain.events.NextRaceReady;
import com.pastimegames.readysetbet.core.domain.events.RaceInitialized;
import com.pastimegames.readysetbet.core.domain.events.WinningsAndPenaltiesDelivered;
import com.pastimegames.readysetbet.core.domain.exceptions.GameLogicException;
import com.pastimegames.shared.datatransferobjects.BetDto;
import com.pastimegames.shared.datatransferobjects.CoinDto;
import com.pastimegames.shared.datatransferobjects.PlayerDto;

public class GameManagerImpl implements GameManager {


    private BookMaker bookMaker;

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
            throw new GameLogicException("Cannot join a game, when it is not in lobby");
        }

        System.out.println("Player joined");
        Player player = new Player(playerDto.playerName(), playerDto.colorCode());

        synchronized (lobby) {
            lobby.join(player);
        }
    }

    @Override
    public void initializeRacing(RaceOptions options) {
        this.options = options;
        if(currentGameState != GameState.IN_LOBBY){
            throw new GameLogicException("Cannot initialize a race outside of the lobby");
        }

        setupRace();
        DomainEventPublisher.instance().publish(new RaceInitialized(options));
    }

    @Override
    public void startRace() {
        if(currentGameState != GameState.RACE_READY){
            throw new GameLogicException("Cannot start er race, before it is ready");
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
    public void placeBet(BetDto betDto) {
        if(currentGameState != GameState.RACE_IN_PROGRESS) {
            throw new GameLogicException("Cannot bet when race is not in progress.");
        }
        CoinDto coin = betDto.coin();
        synchronized (bookMaker) {
            bookMaker.betOnCell(betDto.betPosition(), new Coin(coin.value(), coin.playerName(), coin.color()));
        }
    }

    @Override
    public void displayResults() {
        bookMaker.deliverWinnings(race.getRaceResult(), lobby);
        bookMaker.deliverPenalties(race.getRaceResult(), lobby);

        DomainEventPublisher.instance().publish(new WinningsAndPenaltiesDelivered());
    }

    @Override
    public void nextRace() {
        if(options.numberOfRaces() >= raceNumber){
            throw new GameLogicException("You cannot start more races than initially planned");
        }
        raceNumber++;
        setupRace();
        DomainEventPublisher.instance().publish(new NextRaceReady(raceNumber));
    }

    private void setupRace() {
        race = new Race();
        race.initializeRace();
        currentGameState = GameState.RACE_READY;
        bookMaker = new BookMaker();
        // TODO reset noget betting mht coins?
    }

}
