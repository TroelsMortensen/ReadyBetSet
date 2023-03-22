package com.pastimegames.readysetbet.server.socketserver.handlers;

import com.pastimegames.readysetbet.core.application.gamemanager.GameManager;
import com.pastimegames.readysetbet.core.domain.eventpublisher.DomainEventListener;
import com.pastimegames.readysetbet.core.domain.events.ResultsCalculatedEvent;
import com.pastimegames.shared.datatransferobjects.ResultsCalculatedDto;
import com.pastimegames.shared.datatransferobjects.socketmessages.SocketDto;
import com.pastimegames.shared.datatransferobjects.socketmessages.SocketMessages;

import java.util.function.Consumer;

public class ResultSocketHandler extends SocketHandlerBase {
    public ResultSocketHandler(GameManager gameManager, Consumer<SocketDto> writeToClient) {
        super(gameManager, writeToClient);
    }

    @Override
    protected void setupListeners() {
        subscribe(ResultsCalculatedEvent.type(), (DomainEventListener<ResultsCalculatedEvent>) event -> {
            writeToClient(SocketMessages.Events.Result.RESULTS_CALCULATED, new ResultsCalculatedDto(event.playerBalances()));
        });
    }

    @Override
    public void handle(String command, Object content) {

    }

    @Override
    public String type() {
        return null;
    }
}
