package com.pastimegames.readysetbet.server.presentation.javafxgui.View;

import com.pastimegames.readysetbet.server.presentation.javafxgui.ViewModel.ResultViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ResultsViewController {

    @FXML
    private TextField testResults;

    private ResultViewModel resultsViewModel;

    public void init(ResultViewModel resultsViewModel) {
        this.resultsViewModel = resultsViewModel;
        testResults.textProperty().bind(resultsViewModel.resultsProperty());
    }


}
