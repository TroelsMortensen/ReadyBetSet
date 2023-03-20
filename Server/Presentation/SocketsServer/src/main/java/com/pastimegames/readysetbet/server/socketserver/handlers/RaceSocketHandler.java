package com.pastimegames.readysetbet.server.socketserver.handlers;

import com.pastimegames.readysetbet.core.application.gamemanager.GameManager;
import com.pastimegames.readysetbet.core.domain.eventpublisher.DomainEventListener;
import com.pastimegames.readysetbet.core.domain.eventpublisher.DomainEventPublisher;
import com.pastimegames.readysetbet.core.domain.events.NewRaceReady;
import com.pastimegames.shared.datatransferobjects.socketmessages.SocketDto;

import java.io.IOException;
import java.io.ObjectOutputStream;

public class RaceSocketHandler extends SocketHandlerBase{
    private ObjectOutputStream output;

    public RaceSocketHandler(GameManager gameManager, ObjectOutputStream output) {
        super(gameManager);
        this.output = output;
        setupListeners();
    }

    protected void setupListeners() {
        DomainEventPublisher.instance().subscribe(NewRaceReady.type(), (DomainEventListener<NewRaceReady>) event -> {
            try {
                output.writeObject(new SocketDto("gotoraceview", null));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public void handle(String command, Object content) {

    }
}
