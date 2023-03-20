package com.pastimegames.readysetbet.server.socketserver.handlers;

import com.pastimegames.readysetbet.core.application.gamemanager.GameManager;

public abstract class SocketHandlerBase {

    protected GameManager gameManager;

    public SocketHandlerBase(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    protected abstract void setupListeners();

    public abstract void handle(String command, Object content);

    public String type(){
        return getClass().getSimpleName().toLowerCase().replace("socket", "").replace("handler", "");
    }
}
