package com.pastimegames.readysetbet.server.presentation.javafxgui.View;

import com.pastimegames.readysetbet.server.presentation.javafxgui.Core.ViewHandler;
import com.pastimegames.readysetbet.server.presentation.javafxgui.ViewModel.ResultViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ResultsViewController {

    @FXML
    private TextField testResults;

    private ResultViewModel resultsViewModel;
    private ViewHandler viewHandler;

    public void init(ViewHandler viewHandler, ResultViewModel resultsViewModel)
    {
        this.resultsViewModel = resultsViewModel;
        this.viewHandler = viewHandler;
        testResults.textProperty().bind(resultsViewModel.resultsProperty());
    }

    @FXML
    private void onButtonGoToNextRace()
    {
        viewHandler.openRaceView();
        resultsViewModel.goToNextRace();
    }


}
