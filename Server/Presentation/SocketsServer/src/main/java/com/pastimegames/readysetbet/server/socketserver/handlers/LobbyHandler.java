package com.pastimegames.readysetbet.server.socketserver.handlers;

import com.pastimegames.readysetbet.core.application.gamemanager.GameManager;
import com.pastimegames.shared.datatransferobjects.PlayerDto;

import java.io.ObjectInputStream;

public class LobbyHandler {
    private final ObjectInputStream input;

    public LobbyHandler(ObjectInputStream input) {
        this.input = input;
    }

    public void handle(String actionType, Object content, GameManager gameManager) {
        switch (actionType) {
            case "join" -> join((PlayerDto) content, gameManager);
            case "leave" -> leave((PlayerDto) content, gameManager);
        }
    }

    private void leave(PlayerDto content, GameManager gameManager) {
        gameManager.removePlayerFromLobby(content.playerName());
    }

    private void join(PlayerDto playerDto, GameManager gameManager)  {
        gameManager.joinPlayer(playerDto);
    }
}
