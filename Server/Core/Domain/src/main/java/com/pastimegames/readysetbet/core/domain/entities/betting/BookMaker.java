package com.pastimegames.readysetbet.core.domain.entities.betting;

public class BookMaker {

    private BettingBoard bettingBoard;

    public BookMaker() {
        bettingBoard = new BettingBoard();
    }

    public void betOnCell(int index, Coin coin){
        bettingBoard.placeBetOnCell(index, coin);
    }
}
