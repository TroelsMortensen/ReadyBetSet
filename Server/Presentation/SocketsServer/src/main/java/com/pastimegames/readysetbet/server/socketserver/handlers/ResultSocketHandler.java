package com.pastimegames.readysetbet.server.socketserver.handlers;

import com.pastimegames.readysetbet.core.application.gamemanager.GameManager;
import com.pastimegames.readysetbet.core.domain.eventpublisher.DomainEventListener;
import com.pastimegames.readysetbet.core.domain.eventpublisher.DomainEventPublisher;
import com.pastimegames.readysetbet.core.domain.events.ResultsCalculated;
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
        DomainEventPublisher.instance().subscribe(ResultsCalculated.type(), (DomainEventListener<ResultsCalculated>) event -> {
            writeToClient.accept(new SocketDto(SocketMessages.Events.Result.RESULTS_CALCULATED, new ResultsCalculatedDto(event.playerSaldos())));
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
