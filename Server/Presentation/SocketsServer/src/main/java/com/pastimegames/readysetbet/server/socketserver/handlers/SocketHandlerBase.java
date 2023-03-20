package com.pastimegames.readysetbet.server.socketserver.handlers;

import com.pastimegames.readysetbet.core.application.gamemanager.GameManager;

public abstract class SocketHandlerBase {

    protected GameManager gameManager;

    public SocketHandlerBase(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    protected abstract void setupListeners();
}
