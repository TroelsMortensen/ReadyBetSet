package com.pastimegames.readysetbet.core.domain.entities.betting;

import com.pastimegames.readysetbet.core.domain.eventpublisher.DomainEventPublisher;
import com.pastimegames.readysetbet.core.domain.events.BetPlacedOnCell;
import com.pastimegames.readysetbet.core.domain.exceptions.DomainLogicException;

import java.util.ArrayList;
import java.util.List;

class BettingBoard {

    private List<BetCell> betCells = new ArrayList<>();
    private BetCell[] cells = new BetCell[]{
            /* 1 */ new BetCell(4, -4), new BetCell(4, -3), new BetCell(5, -4), new BetCell(5, -3), new BetCell(7, -2), new BetCell(8, -2), new BetCell(9, -2),
            /* 2 */ new BetCell(3, -1), new BetCell(3, 0), new BetCell(4, -1), new BetCell(4, 0), new BetCell(5, -1), new BetCell(6, 0), new BetCell(7, 0),
            /* 3 */ new BetCell(2, -3), new BetCell(2, 0), new BetCell(2, -2), new BetCell(3, -2), new BetCell(4, -2), new BetCell(4, 0), new BetCell(5, 0),
            /* 4 */ new BetCell(1, -2), new BetCell(1, 0), new BetCell(2, -5), new BetCell(2, -4), new BetCell(3, -2), new BetCell(3, -1), new BetCell(3, 0),
            /* 5 */ new BetCell(1, -3), new BetCell(1, -1), new BetCell(2, -6), new BetCell(2, -5), new BetCell(3, -4), new BetCell(3, -3), new BetCell(3, -2),
            /* 6 */ new BetCell(1, -2), new BetCell(1, 0), new BetCell(2, -5), new BetCell(2, -4), new BetCell(3, -2), new BetCell(3, -1), new BetCell(3, 0),
            /* 7 */ new BetCell(2, -3), new BetCell(2, 0), new BetCell(2, -2), new BetCell(3, -2), new BetCell(4, -2), new BetCell(4, 0), new BetCell(5, 0),
            /* 8 */ new BetCell(3, -1), new BetCell(3, 0), new BetCell(4, -1), new BetCell(4, 0), new BetCell(5, -1), new BetCell(6, 0), new BetCell(7, 0),
            /* 9 */ new BetCell(4, -4), new BetCell(4, -3), new BetCell(5, -4), new BetCell(5, -3), new BetCell(7, -2), new BetCell(8, -2), new BetCell(9, -2)
    };


    BettingBoard() {
        for (int i = 1; i <= cells.length; i++) {
            cells[i].setId(i);
            betCells.add(cells[i]);
        }
    }

    void placeBetOnCell(int index, Coin coin) {
        BetCell betCell = betCells.get(index);
        if (betCell.containsCoin()) {
            throw new DomainLogicException("Cannot place two coins on one cell");
        }

        DomainEventPublisher.instance().publish(new BetPlacedOnCell(index, coin.value(), coin.owningPlayer()));
    }

    void betOnExoticFinish() {
        throw new UnsupportedOperationException();
    }

    void betOnPropBet() {
        throw new UnsupportedOperationException();
    }

    void betOnColorFinish() {
        throw new UnsupportedOperationException();
    }

    public List<BetCell> betCells() {
        return betCells;
    }

    String betCellIdToHorseName(int index) {
        String[] names = new String[]{"2/3", "4", "5", "6", "7", "8", "9", "10", "11/12"};
        for (int i = 1; i < 9; i++) {
            if (index <= 7 * i) return names[i];
        }
        throw new RuntimeException("Failure here");
    }

    public String betCellIdPlacement(int id) {
        int place = id % 8;
        if(place <= 2) return "SHOW";
        if(place <= 4) return "PLACE";
        return "WIN";
    }
}
