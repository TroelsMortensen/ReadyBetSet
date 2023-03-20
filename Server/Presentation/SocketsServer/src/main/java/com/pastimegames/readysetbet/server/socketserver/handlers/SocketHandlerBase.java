package com.pastimegames.readysetbet.server.socketserver.handlers;

import com.pastimegames.readysetbet.core.application.gamemanager.GameManager;
import com.pastimegames.shared.datatransferobjects.socketmessages.SocketDto;

import java.util.function.Consumer;

public abstract class SocketHandlerBase {

    protected GameManager gameManager;
    protected final Consumer<SocketDto> writeToClient;

    public SocketHandlerBase(GameManager gameManager, Consumer<SocketDto> writeToClient) {
        this.gameManager = gameManager;
        this.writeToClient = writeToClient;
    }

    protected abstract void setupListeners();

    public abstract void handle(String command, Object content);

    public String type(){
        return getClass().getSimpleName().toLowerCase().replace("socket", "").replace("handler", "");
    }
}
