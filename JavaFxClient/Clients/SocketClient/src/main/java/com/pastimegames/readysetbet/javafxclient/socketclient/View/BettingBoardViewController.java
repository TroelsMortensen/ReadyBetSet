package com.pastimegames.readysetbet.javafxclient.socketclient.View;

import com.pastimegames.readysetbet.javafxclient.socketclient.Core.ViewHandler;
import com.pastimegames.readysetbet.javafxclient.socketclient.ViewModel.BettingBoardViewModel;
import com.pastimegames.readysetbet.javafxclient.socketclient.ViewModel.ModelRepresentations.CoinRepresentation;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.List;

public class BettingBoardViewController {

    @FXML
    private HBox hBoxCoins;
    @FXML
    private GridPane gridPaneBettingBoard;
    private BettingBoardViewModel bettingBoardViewModel;
    private List<Button> bettingButtons;

    public void init(BettingBoardViewModel bettingBoardViewModel) {
        createBettingGrid();
        this.bettingBoardViewModel = bettingBoardViewModel;
        createButtonsForCoins();
        bettingBoardViewModel.setOnBetPlaced(index -> disableButtonForBet(index));
    }

    private void createBettingGrid()
    {
        bettingButtons = new ArrayList<>();
        int index = 0;
        for (int i = 0; i < 9; i++)
        {
            for (int j = 1; j < 8; j++)
            {
                final int indexForPlaceBetMethod = index;
                Button buttonForBet = new Button(Integer.toString(index++));
                buttonForBet.setPrefSize(40,40);
                buttonForBet.setOnAction(event -> placeBet(indexForPlaceBetMethod));
                gridPaneBettingBoard.add(buttonForBet, j,i);
                bettingButtons.add(buttonForBet);
            }
        }
    }

    private void createButtonsForCoins()
    {
        List<CoinRepresentation> coinRepresentationList = bettingBoardViewModel.getCoins();
        for (CoinRepresentation coin : coinRepresentationList)
        {
            Button buttonForCoin = new Button(Integer.toString(coin.getValue()));
            buttonForCoin.disableProperty().bind(coin.isUsed());
            hBoxCoins.getChildren().add(buttonForCoin);
            buttonForCoin.setOnAction(event -> setSelectedCoin(coin));
        }
    }

    private void setSelectedCoin(CoinRepresentation coin)
    {
        bettingBoardViewModel.setSelectedCoin(coin);
    }

    private void placeBet(int index)
    {
        bettingBoardViewModel.placeBet(index);
    }

    private void disableButtonForBet(int index)
    {
        bettingButtons.get(index).setDisable(true);
    }
}
