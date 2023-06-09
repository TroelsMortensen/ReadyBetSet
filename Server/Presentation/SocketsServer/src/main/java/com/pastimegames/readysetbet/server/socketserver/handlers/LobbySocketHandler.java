package com.pastimegames.readysetbet.server.socketserver.handlers;

import com.pastimegames.readysetbet.core.application.gamemanager.GameManager;
import com.pastimegames.readysetbet.core.domain.entities.lobby.RaceOptions;
import com.pastimegames.readysetbet.core.domain.eventpublisher.DomainEventListener;
import com.pastimegames.readysetbet.core.domain.events.PlayerJoinedLobbyEvent;
import com.pastimegames.readysetbet.core.domain.events.PlayerLeftLobbyEvent;
import com.pastimegames.readysetbet.core.domain.events.LobbyFinalizedEvent;
import com.pastimegames.shared.datatransferobjects.PlayerDto;
import com.pastimegames.shared.datatransferobjects.PlayerJoinedLobbyDto;
import com.pastimegames.shared.datatransferobjects.PlayerLeftLobbyDto;
import com.pastimegames.shared.datatransferobjects.RaceOptionsDto;
import com.pastimegames.shared.datatransferobjects.socketmessages.SocketDto;
import com.pastimegames.shared.datatransferobjects.socketmessages.SocketMessages;

import java.util.function.Consumer;

public class LobbySocketHandler extends SocketHandlerBase {

    public LobbySocketHandler(GameManager gameManager, Consumer<SocketDto> writeToClient) {
        super(gameManager, writeToClient);
    }

    @Override
    protected void setupListeners() {
        subscribe(PlayerJoinedLobbyEvent.type(), (DomainEventListener<PlayerJoinedLobbyEvent>) event ->{
            PlayerJoinedLobbyDto dto = new PlayerJoinedLobbyDto(event.name(), event.colorCode());
            writeToClient(SocketMessages.Events.Lobby.PLAYER_JOINED, dto);
        });

        subscribe(PlayerLeftLobbyEvent.type(), (DomainEventListener<PlayerLeftLobbyEvent>) event -> {
            PlayerLeftLobbyDto dto = new PlayerLeftLobbyDto(event.name());
            writeToClient(SocketMessages.Events.Lobby.PLAYER_LEFT, dto);
        });
        subscribe(LobbyFinalizedEvent.type(), (DomainEventListener<LobbyFinalizedEvent>) event -> {
            RaceOptions op = event.options();
            RaceOptionsDto options = new RaceOptionsDto(op.numberOfRaces(), op.moveTickTime(), op.numberOfAllowedBidsPerCell());
            writeToClient(SocketMessages.Events.Lobby.LOBBY_FINALIZED, options);
        });
    }

    public void handle(String actionType, Object content) {
        switch (actionType) {
            case SocketMessages.Commands.Lobby.JOIN -> join((PlayerDto) content);
            case SocketMessages.Commands.Lobby.LEAVE -> leave((PlayerDto) content);
            default -> noEndPointFound(actionType);
        }
    }

    @Override
    public String type() {
        return SocketMessages.Commands.Lobby.BASE_URL;
    }

    private void leave(PlayerDto content) {
        gameManager.leaveLobby(content.playerName());
    }

    private void join(PlayerDto playerDto) {
        gameManager.joinLobby(playerDto);
    }
}
