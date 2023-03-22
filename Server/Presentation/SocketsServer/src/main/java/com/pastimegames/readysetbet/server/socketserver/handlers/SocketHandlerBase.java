package com.pastimegames.readysetbet.server.socketserver.handlers;

import com.pastimegames.readysetbet.core.application.gamemanager.GameManager;
import com.pastimegames.readysetbet.core.domain.eventpublisher.DomainEventListener;
import com.pastimegames.readysetbet.core.domain.eventpublisher.DomainEventPublisher;
import com.pastimegames.shared.datatransferobjects.socketmessages.SocketDto;
import com.pastimegames.shared.datatransferobjects.socketmessages.SocketMessages;

import java.util.function.Consumer;

public abstract class SocketHandlerBase {

    protected GameManager gameManager;
    protected final Consumer<SocketDto> writeToClient;

    public SocketHandlerBase(GameManager gameManager, Consumer<SocketDto> writeToClient) {
        this.gameManager = gameManager;
        this.writeToClient = writeToClient;
    }

    protected abstract void setupListeners();

    protected void noEndPointFound(String command) {
        SocketDto error = new SocketDto(SocketMessages.Events.ERROR, "No endpoint matching " + command);
        writeToClient.accept(error);
    }

    public abstract void handle(String command, Object content);

    public abstract String type();

    protected <T> void subscribe(String eventType, DomainEventListener<T> listener){
        DomainEventPublisher.instance().subscribe(eventType, listener);
    }

    protected void writeToClient(String type, Object obj){
        writeToClient.accept(new SocketDto(type, obj));
    }
}
