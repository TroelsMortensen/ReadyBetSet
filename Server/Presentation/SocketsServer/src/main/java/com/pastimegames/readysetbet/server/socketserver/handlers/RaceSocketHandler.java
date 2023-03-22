package com.pastimegames.readysetbet.server.socketserver.handlers;

import com.pastimegames.readysetbet.core.application.gamemanager.GameManager;
import com.pastimegames.readysetbet.core.domain.eventpublisher.DomainEventListener;
import com.pastimegames.readysetbet.core.domain.events.*;
import com.pastimegames.shared.datatransferobjects.HorseMovedDto;
import com.pastimegames.shared.datatransferobjects.socketmessages.SocketDto;
import com.pastimegames.shared.datatransferobjects.socketmessages.SocketMessages;

import java.util.function.Consumer;

public class RaceSocketHandler extends SocketHandlerBase {

    public RaceSocketHandler(GameManager gameManager, Consumer<SocketDto> writeToClient) {
        super(gameManager, writeToClient);
    }

    protected void setupListeners() {
        subscribe(NextRaceReadyEvent.type(), (DomainEventListener<NextRaceReadyEvent>) event -> {
            writeToClient(SocketMessages.Events.Result.RACE_VIEW_SELECTED, null);
        });

        subscribe(RaceStartedEvent.type(), (DomainEventListener<RaceStartedEvent>) event -> {
            writeToClient(SocketMessages.Events.Race.RACE_STARTED, null);
        });

        subscribe(NextRaceReadyEvent.type(), (DomainEventListener<NextRaceReadyEvent>) event -> {
            writeToClient(SocketMessages.Events.Race.NEXT_RACE_READY, event.raceNumber());
        });
        subscribe(HorseMovedEvent.type(), (DomainEventListener<HorseMovedEvent>) horse -> {
            writeToClient(SocketMessages.Events.Race.HORSE_MOVED, new HorseMovedDto(horse.horseName(), horse.currentPosition()));
        });
        subscribe(RaceFinishedEvent.type(), (DomainEventListener<RaceFinishedEvent>) event -> {
            writeToClient(SocketMessages.Events.Race.RACE_FINISHED, event.winnerHorseName());
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
