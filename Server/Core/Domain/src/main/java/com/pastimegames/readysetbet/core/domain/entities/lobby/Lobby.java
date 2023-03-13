package com.pastimegames.readysetbet.core.domain.entities.lobby;

import com.pastimegames.readysetbet.core.domain.eventpublisher.DomainEventPublisher;
import com.pastimegames.readysetbet.core.domain.events.PlayerJoinedLobby;
import com.pastimegames.readysetbet.core.domain.events.PlayerLeftLobby;

import java.util.ArrayList;
import java.util.List;

public class Lobby {
    private final List<Player> players = new ArrayList<>();

    public void joinLobby(Player player){
        boolean playerNameTaken = players.stream().anyMatch(p -> p.name().equals(player.name()));
        if(playerNameTaken){
            throw new RuntimeException("Player name '" + player.name() + "' already in lobby. It must be unique");
        }
        players.add(player);
        DomainEventPublisher.instance().publish(new PlayerJoinedLobby(player.name(), player.colorCode()));
    }

    public void leaveLobby(String playerNameToRemove) {
        players.removeIf(player -> player.name().equals(playerNameToRemove));
        DomainEventPublisher.instance().publish(new PlayerLeftLobby(playerNameToRemove));
    }
}
