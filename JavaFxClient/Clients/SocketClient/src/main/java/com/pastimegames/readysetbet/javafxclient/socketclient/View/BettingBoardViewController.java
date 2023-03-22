package com.pastimegames.readysetbet.javafxclient.socketclient.View;

import com.pastimegames.readysetbet.javafxclient.socketclient.Core.ViewHandler;
import com.pastimegames.readysetbet.javafxclient.socketclient.ViewModel.BettingBoardViewModel;
import com.pastimegames.readysetbet.javafxclient.socketclient.ViewModel.CoinRepresentation;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.util.List;

public class BettingBoardViewController {

    @FXML
    private Button button;
    @FXML
    private HBox hBoxCoins;
    @FXML
    private GridPane gridPaneBettingBoard;
    private BettingBoardViewModel bettingBoardViewModel;

    public void init(ViewHandler viewHandler, BettingBoardViewModel bettingBoardViewModel) {
        //gridPaneBettingBoard.add(new Button("test"), 1,1);
        this.bettingBoardViewModel = bettingBoardViewModel;
        createButtonsForCoins();
    }

    private void createButtonsForCoins()
    {
        List<CoinRepresentation> coinRepresentationList = bettingBoardViewModel.getCoins();
        for (CoinRepresentation coin : coinRepresentationList)
        {
            Button buttonForCoin = new Button(Integer.toString(coin.getValue()));
            buttonForCoin.disableProperty().bind(coin.isAvailable());
            hBoxCoins.getChildren().add(buttonForCoin);
            buttonForCoin.setOnAction(event -> setSelectedCoin(coin));
        }
    }

    private void setSelectedCoin(CoinRepresentation coin)
    {
        bettingBoardViewModel.setSelectedCoin(coin);
    }

    @FXML
    private void placeBet()
    {
        bettingBoardViewModel.placeBet(2);
    }
}
