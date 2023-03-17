package com.pastimegames.readysetbet.core.domain.entities.betting;

import com.pastimegames.readysetbet.core.domain.eventpublisher.DomainEventPublisher;

import java.util.ArrayList;
import java.util.List;

public class BettingBoard {

    private List<BetCell> betCells = new ArrayList<>();
    private BetCell[] cells = new BetCell[]{
            /* 1 */ new BetCell(4, -4), new BetCell(4, -3), new BetCell(5, -4), new BetCell(5, -3), new BetCell(7, -2), new BetCell(8, -2), new BetCell(9, -2),
            /* 2 */ new BetCell(3, -1),
            /* 3 */ new BetCell(2, -3),
            /* 4 */ new BetCell(1, -2),
            /* 5 */ new BetCell(0, 0),
            /* 6 */ new BetCell(1, -2),
            /* 7 */ new BetCell(2, -3),
            /* 8 */ new BetCell(3, -1),
            /* 9 */ new BetCell(4, -4), new BetCell(4, -3), new BetCell(5, -4), new BetCell(5, -3), new BetCell(7, -2), new BetCell(8, -2), new BetCell(9, -2)
    };

// TODO make the rest of the rows here

    public BettingBoard() {
        for (int i = 0; i < cells.length; i++) {
            cells[i].setId(i);
            betCells.add(cells[i]);
        }
    }

    public void betOnCell() {


//        DomainEventPublisher.instance().publish();
    }

    public void betOnExoticFinish() {

    }

    public void betOnPropBet() {

    }

    public void betOnColorFinish() {

    }
}
