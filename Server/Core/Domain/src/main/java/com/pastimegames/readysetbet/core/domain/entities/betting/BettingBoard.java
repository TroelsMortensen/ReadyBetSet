package com.pastimegames.readysetbet.core.domain.entities.betting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BettingBoard {

    private List<BetCell> betCells = new ArrayList<>();
    private BetCell[] cells = new BetCell[]{
            new BetCell(4, -4), new BetCell(4, -3), new BetCell(5, -4), new BetCell(5, -3), new BetCell(7, -2), new BetCell(8, -2), new BetCell(9, -2),
// TODO make the rest of the rows here
    };

    public BettingBoard() {
        for (int i = 0; i < cells.length; i++) {
            cells[i].setId(i);
            betCells.add(cells[i]);
        }
    }

    public void betOnCell() {

    }

    public void betOnExoticFinish() {

    }

    public void betOnPropBet() {

    }

    public void betOnColorFinish() {

    }
}
