package com.pastimegames.readysetbet.core.domain.entities.lobby;

import com.pastimegames.readysetbet.core.domain.eventpublisher.DomainEventPublisher;
import com.pastimegames.readysetbet.core.domain.events.PlayerJoinedLobby;
import com.pastimegames.readysetbet.core.domain.events.PlayerLeftLobby;
import com.pastimegames.readysetbet.core.domain.exceptions.DomainLogicException;

import java.util.ArrayList;
import java.util.List;

public class Lobby {
    private final List<Player> lobbyPlayerList = new ArrayList<>();

    public void joinLobby(Player player){
        boolean playerNameTaken = lobbyPlayerList.stream().anyMatch(p -> p.name().equals(player.name()));
        if(playerNameTaken){
            throw new DomainLogicException("Player name '" + player.name() + "' already in lobby. Name must be unique");
        }
        lobbyPlayerList.add(player);
        DomainEventPublisher.instance().publish(new PlayerJoinedLobby(player.name(), player.colorCode()));
    }

    public void leaveLobby(String playerNameToRemove) {
        lobbyPlayerList.removeIf(player -> player.name().equals(playerNameToRemove));
        DomainEventPublisher.instance().publish(new PlayerLeftLobby(playerNameToRemove));
    }
}
