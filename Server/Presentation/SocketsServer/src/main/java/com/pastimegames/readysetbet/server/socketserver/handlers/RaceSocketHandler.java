package com.pastimegames.readysetbet.server.socketserver.handlers;

import com.pastimegames.readysetbet.core.application.gamemanager.GameManager;
import com.pastimegames.readysetbet.core.domain.eventpublisher.DomainEventListener;
import com.pastimegames.readysetbet.core.domain.eventpublisher.DomainEventPublisher;
import com.pastimegames.readysetbet.core.domain.events.NewRaceReady;
import com.pastimegames.shared.datatransferobjects.socketmessages.SocketDto;

import java.io.IOException;
import java.io.ObjectOutputStream;

public class RaceSocketHandler {
    private GameManager gameManager;
    private ObjectOutputStream output;

    public RaceSocketHandler(GameManager gameManager, ObjectOutputStream output) {

        this.gameManager = gameManager;
        this.output = output;
        setupListeners();
    }

    private void setupListeners() {
        DomainEventPublisher.instance().subscribe(NewRaceReady.type(), (DomainEventListener<NewRaceReady>) event -> {
            try {
                output.writeObject(new SocketDto("gotoraceview", null));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
