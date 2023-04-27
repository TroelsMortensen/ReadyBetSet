package com.pastimegames.readysetbet.javafxclient.socketclient.View;

import com.pastimegames.readysetbet.javafxclient.socketclient.ViewModel.BettingBoardViewModel;
import com.pastimegames.readysetbet.javafxclient.socketclient.ViewModel.ModelRepresentations.CoinRepresentation;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.beans.PropertyChangeEvent;
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
        this.bettingBoardViewModel = bettingBoardViewModel;
        createBettingGrid();
        createButtonsForCoins();
        bettingBoardViewModel.addPropertyChangeListener("BET_PLACED_ON_INDEX", this::disableButtonForBet);
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
        List<CoinRepresentation> coinRepresentations = bettingBoardViewModel.getCoinRepresentations();
        for (CoinRepresentation coinRepresentation : coinRepresentations)
        {
            Button buttonForCoin = new Button(Integer.toString(coinRepresentation.getValue()));
            buttonForCoin.disableProperty().bind(coinRepresentation.getIsUsedProperty());
            hBoxCoins.getChildren().add(buttonForCoin);
            buttonForCoin.setOnAction(event -> setSelectedCoin(coinRepresentation));
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

    private void disableButtonForBet(PropertyChangeEvent propertyChangeEvent)
    {
        int index = (int) propertyChangeEvent.getNewValue();
        Button button = bettingButtons.get(index);
        button.setDisable(true);
    }
}
