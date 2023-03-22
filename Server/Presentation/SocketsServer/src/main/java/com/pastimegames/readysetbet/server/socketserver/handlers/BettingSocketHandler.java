package com.pastimegames.readysetbet.server.socketserver.handlers;

import com.pastimegames.readysetbet.core.application.gamemanager.GameManager;
import com.pastimegames.readysetbet.core.domain.eventpublisher.DomainEventListener;
import com.pastimegames.readysetbet.core.domain.eventpublisher.DomainEventPublisher;
import com.pastimegames.readysetbet.core.domain.events.BetPlacedOnCell;
import com.pastimegames.readysetbet.core.domain.events.BetsAreClosed;
import com.pastimegames.shared.datatransferobjects.BetDto;
import com.pastimegames.shared.datatransferobjects.BetPlacedOnCellDto;
import com.pastimegames.shared.datatransferobjects.socketmessages.SocketDto;
import com.pastimegames.shared.datatransferobjects.socketmessages.SocketMessages;

import java.io.OutputStream;
import java.util.function.Consumer;

public class BettingSocketHandler extends SocketHandlerBase {

    public BettingSocketHandler(GameManager gameManager, Consumer<SocketDto> writeToClient) {
        super(gameManager, writeToClient);
    }

    @Override
    protected void setupListeners() {
        subscribe(BetPlacedOnCell.type(), (DomainEventListener<BetPlacedOnCell>) event -> {
            BetPlacedOnCellDto content = new BetPlacedOnCellDto(event.index(), event.coinValue(), event.owningPlayer(), event.color());
            writeToClient.accept(new SocketDto(SocketMessages.Events.Betting.BET_PLACED, content));
        });


        subscribe(BetsAreClosed.type(), (DomainEventListener<BetsAreClosed>) event -> {
            writeToClient.accept(new SocketDto(SocketMessages.Events.Betting.BETS_ARE_CLOSED, null));
        });
    }

    @Override
    public void handle(String command, Object content) {
        switch (command) {
            case SocketMessages.Commands.Betting.BET_ON_CELL -> placeCellBet((BetDto) content);
            case SocketMessages.Commands.Betting.BET_ON_PROP_CARD -> placePropCardBet((BetDto) content);
            default -> noEndPointFound(command);
        }
    }

    @Override
    public String type() {
        return SocketMessages.Commands.Betting.BASE_URL;
    }


    private void placePropCardBet(BetDto content) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void placeCellBet(BetDto bet) {
        gameManager.placeBet(bet);
    }

}
