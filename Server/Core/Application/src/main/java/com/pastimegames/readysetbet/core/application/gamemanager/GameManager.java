package com.pastimegames.readysetbet.core.application.gamemanager;

import com.pastimegames.readysetbet.core.domain.entities.lobby.Lobby;
import com.pastimegames.readysetbet.core.domain.entities.lobby.Player;
import com.pastimegames.readysetbet.core.domain.eventpublisher.DomainEventPublisher;
import com.pastimegames.readysetbet.core.domain.events.RaceInitialized;
import com.pastimegames.shared.datatransferobjects.PlayerDto;

public class GameManager {

    private enum GameState{
        IN_LOBBY,
        IN_RACE,
        IN_RESULTS
    }

    private Lobby lobby;
    private GameState currenGameState;
    public GameManager() {
        currenGameState = GameState.IN_LOBBY;
        lobby = new Lobby();
    }

    public void joinPlayer(PlayerDto playerdto) {
        System.out.println("Player joined");
        Player player = new Player(playerdto.playerName(), playerdto.colorCode());
        lobby.join(player);
    }

    public void initializeRace(){
        System.out.println("Race initialized");
        DomainEventPublisher.instance().publish(new RaceInitialized());
    }
}
