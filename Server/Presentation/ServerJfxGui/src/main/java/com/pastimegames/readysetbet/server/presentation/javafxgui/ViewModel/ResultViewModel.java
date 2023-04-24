package com.pastimegames.readysetbet.server.presentation.javafxgui.ViewModel;

import com.pastimegames.readysetbet.core.domain.events.ResultsCalculatedEvent;
import com.pastimegames.readysetbet.core.domain.valueobjects.PlayerBalances;
import com.pastimegames.readysetbet.server.presentation.javafxgui.Model.Model;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.beans.PropertyChangeEvent;

public class ResultViewModel {

    private Model model;
    private StringProperty results;

    public ResultViewModel(Model model) {

        this.model = model;
        results = new SimpleStringProperty();
        initializeListeners();
    }

    private void initializeListeners() {
        model.addPropertyChangeListener("RESULTS_CALCULATED", this::onResultsCalculated);
    }

    private void onResultsCalculated(PropertyChangeEvent propertyChangeEvent) {
        String results = "";
        ResultsCalculatedEvent resultsCalculatedEvent = (ResultsCalculatedEvent) propertyChangeEvent.getNewValue();
        PlayerBalances playerBalances = resultsCalculatedEvent.playerBalances();
        for (String playerName : playerBalances.playerNames()) {
            results += playerName + " : " + playerBalances.balanceOfPlayer(playerName) + "\n";
        }
        results += "Winning horse: " + resultsCalculatedEvent.raceResult().getNameOfWinnerHorse() + "\n";
        results += "Show horses: " + resultsCalculatedEvent.raceResult().getNamesOfShowHorses() + "\n";
        results += "Place horses: " + resultsCalculatedEvent.raceResult().getNamesOfPlaceHorses() + "\n";
        this.results.set(results);
    }

    public StringProperty resultsProperty() {
        return results;
    }

    public void goToNextRace() {
        model.goToNextRace();
    }
}
