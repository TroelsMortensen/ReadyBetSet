package com.pastimegames.readysetbet.server.socketserver.handlers;

import com.pastimegames.readysetbet.core.application.gamemanager.GameManager;
import com.pastimegames.readysetbet.core.domain.entities.lobby.RaceOptions;
import com.pastimegames.readysetbet.core.domain.eventpublisher.DomainEventListener;
import com.pastimegames.readysetbet.core.domain.eventpublisher.DomainEventPublisher;
import com.pastimegames.readysetbet.core.domain.events.HorseMoved;
import com.pastimegames.readysetbet.core.domain.events.NextRaceReady;
import com.pastimegames.readysetbet.core.domain.events.RaceInitialized;
import com.pastimegames.readysetbet.core.domain.events.RaceStarted;
import com.pastimegames.shared.datatransferobjects.HorseMovedDto;
import com.pastimegames.shared.datatransferobjects.RaceOptionsDto;
import com.pastimegames.shared.datatransferobjects.socketmessages.SocketDto;

import java.io.ObjectOutputStream;
import java.util.function.Consumer;

public class RaceSocketHandler extends SocketHandlerBase {
    private ObjectOutputStream output;

    public RaceSocketHandler(GameManager gameManager, ObjectOutputStream output, Consumer<SocketDto> writeToClient) {
        super(gameManager, writeToClient);
        this.output = output;
        setupListeners();
    }

    protected void setupListeners() {
        DomainEventPublisher.instance().subscribe(NextRaceReady.type(), (DomainEventListener<NextRaceReady>) event -> {
            writeToClient.accept(new SocketDto("gotoraceview", null));
        });

        DomainEventPublisher.instance().subscribe(RaceStarted.type(), (DomainEventListener<RaceStarted>) event -> {
            writeToClient.accept(new SocketDto("racestarted", null));
        });

        DomainEventPublisher.instance().subscribe(NextRaceReady.type(), (DomainEventListener<NextRaceReady>) event -> {
            writeToClient.accept(new SocketDto("newraceready", event.raceNumber()));
        });

        DomainEventPublisher.instance().subscribe(RaceInitialized.type(), (DomainEventListener<RaceInitialized>) event -> {
            RaceOptions op = event.options();
            RaceOptionsDto options = new RaceOptionsDto(op.numberOfRaces(), op.moveTickTime(), op.numberOfAllowedBidsPerCell());
            writeToClient.accept(new SocketDto("raceinitialized", options));
        });

        DomainEventPublisher.instance().subscribe(HorseMoved.type(), (DomainEventListener<HorseMoved>) horse -> {
            writeToClient.accept(new SocketDto("horsemoved", new HorseMovedDto(horse.horseName(), horse.currentPosition())));
        });


    }

    @Override
    public void handle(String command, Object content) {
        switch (command){
            default -> noEndPointFound(command);
        }
    }
}
