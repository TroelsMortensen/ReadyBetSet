package com.pastimegames.readysetbet.server.socketserver.handlers;

import com.pastimegames.readysetbet.core.application.gamemanager.GameManager;
import com.pastimegames.shared.datatransferobjects.PlayerDto;
import com.pastimegames.shared.datatransferobjects.socketmessages.SocketDto;

import java.util.function.Consumer;

public class LobbySocketHandler extends SocketHandlerBase {

    public LobbySocketHandler(GameManager gameManager, Consumer<SocketDto> writeToClient) {
        super(gameManager, writeToClient);
    }

    @Override
    protected void setupListeners() {

    }

    public void handle(String actionType, Object content) {
        switch (actionType) {
            case "join" -> join((PlayerDto) content);
            case "leave" -> leave((PlayerDto) content);
            default -> noEndPointFound(actionType);
        }
    }

    private void leave(PlayerDto content) {
        gameManager.removePlayerFromLobby(content.playerName());
    }

    private void join(PlayerDto playerDto) {
        gameManager.joinPlayer(playerDto);
    }
}
