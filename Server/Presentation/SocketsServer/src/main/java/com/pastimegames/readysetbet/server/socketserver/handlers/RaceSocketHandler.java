package com.pastimegames.readysetbet.server.socketserver.handlers;

import com.pastimegames.readysetbet.core.application.gamemanager.GameManager;
import com.pastimegames.readysetbet.core.domain.eventpublisher.DomainEventListener;
import com.pastimegames.readysetbet.core.domain.eventpublisher.DomainEventPublisher;
import com.pastimegames.readysetbet.core.domain.events.HorseMoved;
import com.pastimegames.readysetbet.core.domain.events.NextRaceReady;
import com.pastimegames.readysetbet.core.domain.events.RaceFinished;
import com.pastimegames.readysetbet.core.domain.events.RaceStarted;
import com.pastimegames.shared.datatransferobjects.HorseMovedDto;
import com.pastimegames.shared.datatransferobjects.socketmessages.SocketDto;
import com.pastimegames.shared.datatransferobjects.socketmessages.SocketMessages;

import java.io.ObjectOutputStream;
import java.util.function.Consumer;

public class RaceSocketHandler extends SocketHandlerBase {

    public RaceSocketHandler(GameManager gameManager, Consumer<SocketDto> writeToClient) {
        super(gameManager, writeToClient);
        setupListeners();
    }

    protected void setupListeners() {
        DomainEventPublisher.instance().subscribe(NextRaceReady.type(), (DomainEventListener<NextRaceReady>) event -> {
            writeToClient.accept(new SocketDto(SocketMessages.Events.Race.GO_TO_RACE_VIEW, null));
        });

        DomainEventPublisher.instance().subscribe(RaceStarted.type(), (DomainEventListener<RaceStarted>) event -> {
            writeToClient.accept(new SocketDto(SocketMessages.Events.Race.RACE_STARTED, null));
        });

        DomainEventPublisher.instance().subscribe(NextRaceReady.type(), (DomainEventListener<NextRaceReady>) event -> {
            writeToClient.accept(new SocketDto(SocketMessages.Events.Race.NEXT_RACE_READY, event.raceNumber()));
        });
        DomainEventPublisher.instance().subscribe(HorseMoved.type(), (DomainEventListener<HorseMoved>) horse -> {
            writeToClient.accept(new SocketDto(SocketMessages.Events.Race.HORSE_MOVED, new HorseMovedDto(horse.horseName(), horse.currentPosition())));
        });
        DomainEventPublisher.instance().subscribe(RaceFinished.type(), (DomainEventListener<RaceFinished>) event -> {
            writeToClient.accept(new SocketDto(SocketMessages.Events.Race.RACE_FINISHED, event.winnerHorseName()));
        });

    }

    @Override
    public void handle(String command, Object content) {
        switch (command) {
            default -> noEndPointFound(command);
        }
    }

    @Override
    public String type() {
        return SocketMessages.Commands.Race.BASE_URL;
    }
}
