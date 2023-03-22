package com.pastimegames.readysetbet.javafxclient.socketclient.View;

import com.pastimegames.readysetbet.javafxclient.socketclient.Core.ViewHandler;
import com.pastimegames.readysetbet.javafxclient.socketclient.Model.Bet;
import com.pastimegames.readysetbet.javafxclient.socketclient.Model.Coin;
import com.pastimegames.readysetbet.javafxclient.socketclient.ViewModel.BettingBoardViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class BettingBoardViewController {

    @FXML
    private Button button;
    @FXML
    private GridPane gridPaneBettingBoard;
    private BettingBoardViewModel bettingBoardViewModel;

    public void init(ViewHandler viewHandler, BettingBoardViewModel bettingBoardViewModel) {
        //gridPaneBettingBoard.add(new Button("test"), 1,1);
        this.bettingBoardViewModel = bettingBoardViewModel;
    }

    @FXML
    private void placeBet()
    {
        bettingBoardViewModel.placeBet(new Bet(2, new Coin(2, "NeoGeo", "purple")));
    }
}
