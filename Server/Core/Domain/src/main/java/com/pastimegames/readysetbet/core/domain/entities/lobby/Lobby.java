package com.pastimegames.readysetbet.core.domain.entities.lobby;

import com.pastimegames.readysetbet.core.domain.eventpublisher.DomainEventPublisher;
import com.pastimegames.readysetbet.core.domain.events.PlayerJoined;

import java.util.ArrayList;
import java.util.List;

public class Lobby {
    private List<Player> players = new ArrayList<>();

    public void join(Player player){
        boolean playerNameTaken = players.stream().anyMatch(p -> p.name().equals(player.name()));
        if(playerNameTaken){
            throw new RuntimeException("Player name '" + player.name() + "' already in lobby. It must be unique");
        }
        players.add(player);
        DomainEventPublisher.instance().publish(new PlayerJoined(player.name(), player.colorCode()));
    }
}
