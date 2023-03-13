package com.pastimegames.readysetbet.core.application.gamemanager;

import com.pastimegames.readysetbet.core.domain.entities.lobby.Lobby;
import com.pastimegames.readysetbet.core.domain.entities.lobby.Player;
import com.pastimegames.readysetbet.core.domain.entities.race.Race;
import com.pastimegames.readysetbet.core.domain.eventpublisher.DomainEventPublisher;
import com.pastimegames.readysetbet.core.domain.events.RaceInitialized;
import com.pastimegames.shared.datatransferobjects.PlayerDto;

public class GameManagerImpl implements GameManager{

    private enum GameState{
        IN_LOBBY,
        IN_RACE,
        IN_RESULTS
    }

    private Lobby lobby;
    private GameState currenGameState;
    private Race race;
    public GameManagerImpl() {
        currenGameState = GameState.IN_LOBBY;
        lobby = new Lobby();
    }

    @Override
    public void joinPlayer(PlayerDto playerdto) {
        System.out.println("Player joined");
        Player player = new Player(playerdto.playerName(), playerdto.colorCode());
        lobby.join(player);
    }

    @Override
    public void initializeRace(){
        System.out.println("Race initialized");
        race = new Race();
        DomainEventPublisher.instance().publish(new RaceInitialized());
    }
}
