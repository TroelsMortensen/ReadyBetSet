package com.pastimegames.readysetbet.server.socketserver.handlers;

import com.pastimegames.readysetbet.core.application.gamemanager.GameManager;
import com.pastimegames.shared.datatransferobjects.socketmessages.SocketDto;

import java.io.OutputStream;
import java.util.function.Consumer;

public class BettingSocketHandler extends SocketHandlerBase {


    private final OutputStream output;

    public BettingSocketHandler(GameManager gameManager, OutputStream output, Consumer<SocketDto> writeToClient) {
        super(gameManager, writeToClient);
        this.output = output;
    }

    @Override
    protected void setupListeners() {

    }

    @Override
    public void handle(String command, Object content) {

    }

}
