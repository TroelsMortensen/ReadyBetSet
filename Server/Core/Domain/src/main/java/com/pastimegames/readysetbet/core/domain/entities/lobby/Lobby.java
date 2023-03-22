package com.pastimegames.readysetbet.core.domain.entities.lobby;

import com.pastimegames.readysetbet.core.domain.eventpublisher.DomainEventPublisher;
import com.pastimegames.readysetbet.core.domain.events.PlayerJoinedLobbyEvent;
import com.pastimegames.readysetbet.core.domain.events.PlayerLeftLobbyEvent;
import com.pastimegames.readysetbet.core.domain.exceptions.DomainLogicException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Lobby {
    private final List<Player> lobbyPlayerList = new ArrayList<>();

    public void join(Player player){
        boolean playerNameTaken = lobbyPlayerList.stream().anyMatch(p -> p.name().equals(player.name()));
        if(playerNameTaken){
            throw new DomainLogicException("Player name '" + player.name() + "' already in lobby. Name must be unique");
        }
        lobbyPlayerList.add(player);
        DomainEventPublisher.instance().publish(new PlayerJoinedLobbyEvent(player.name(), player.colorCode()));
    }

    public void leave(String playerNameToRemove) {
        boolean wasRemoved = lobbyPlayerList.removeIf(player -> player.name().equals(playerNameToRemove));
        if(!wasRemoved){
            throw new DomainLogicException("Could not find player '" + playerNameToRemove + "' to be removed from lobby.");
        }
        DomainEventPublisher.instance().publish(new PlayerLeftLobbyEvent(playerNameToRemove));
    }

    public Player getPlayer(String playerName) {
        return lobbyPlayerList.stream().filter(p -> p.name().equals(playerName)).findFirst().get();
    }

    public Map<String, Integer> getPlayerBalances() {
        return lobbyPlayerList.stream().collect(Collectors.toMap(Player::name, Player::balance));
    }
}
