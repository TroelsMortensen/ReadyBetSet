package com.pastimegames.readysetbet.server.socketserver.handlers;

import com.pastimegames.readysetbet.core.application.gamemanager.GameManager;
import com.pastimegames.readysetbet.core.domain.eventpublisher.DomainEventListener;
import com.pastimegames.readysetbet.core.domain.eventpublisher.DomainEventPublisher;
import com.pastimegames.readysetbet.core.domain.events.BetPlacedOnCell;
import com.pastimegames.shared.datatransferobjects.BetDto;
import com.pastimegames.shared.datatransferobjects.BetPlacedOnCellDto;
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
        DomainEventPublisher.instance().subscribe(BetPlacedOnCell.type(), (DomainEventListener<BetPlacedOnCell>) event -> {
            BetPlacedOnCellDto content = new BetPlacedOnCellDto(event.index(), event.coinValue(), event.owningPlayer(), event.color());
            writeToClient.accept(new SocketDto("BetPlacedOnCell", content));
        });
    }

    @Override
    public void handle(String command, Object content) {
        switch (command) {
            case "cellbet" -> placeCellBet((BetDto)content);
            case "propcardbet" -> placePropCardBet((BetDto) content);
            default -> noEndPointFound(command);
        }
    }



    private void placePropCardBet(BetDto content) {

    }

    private void placeCellBet(BetDto bet) {
        gameManager.placeBet(bet);
    }

}
