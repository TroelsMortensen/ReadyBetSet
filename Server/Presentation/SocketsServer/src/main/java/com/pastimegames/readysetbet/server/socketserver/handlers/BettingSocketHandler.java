package com.pastimegames.readysetbet.server.socketserver.handlers;

import com.pastimegames.readysetbet.core.application.gamemanager.GameManager;

import java.io.OutputStream;

public class BettingSocketHandler extends SocketHandlerBase {


    private final OutputStream output;

    public BettingSocketHandler(GameManager gameManager, OutputStream output) {
        super(gameManager);
        this.output = output;
    }

    @Override
    protected void setupListeners() {

    }
}
