package com.pastimegames.readysetbet.server.presentation.javafxgui.View;

import com.pastimegames.readysetbet.server.presentation.javafxgui.Core.ViewHandler;
import com.pastimegames.readysetbet.server.presentation.javafxgui.ViewModel.ViewModelHorse;
import com.pastimegames.readysetbet.server.presentation.javafxgui.ViewModel.RaceViewModel;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;

public class RaceViewController
{

  @FXML
  private GridPane gridRace;
  @FXML
  private Button buttonStartRace;

  private RaceViewModel raceViewModel;
  private ViewHandler viewHandler;
  private List<Label> horsesAsLabels;

  public void init(RaceViewModel raceViewModel, ViewHandler viewHandler)
  {
    this.raceViewModel = raceViewModel;
    this.viewHandler = viewHandler;
    buttonStartRace.disableProperty().bind(raceViewModel.getStartButtonProperty());

    initializeUIHorses();
    initializeGridPane();
    registerListeners();
  }

  private void initializeGridPane() {
    gridRace.add(horsesAsLabels.get(0), 0,1);
    gridRace.add(horsesAsLabels.get(1), 0,2);
    gridRace.add(horsesAsLabels.get(2), 0,3);
    gridRace.add(horsesAsLabels.get(3), 0,4);
    gridRace.add(horsesAsLabels.get(4), 0,5);
    gridRace.add(horsesAsLabels.get(5), 0,6);
    gridRace.add(horsesAsLabels.get(6), 0,7);
    gridRace.add(horsesAsLabels.get(7), 0,8);
    gridRace.add(horsesAsLabels.get(8), 0,9);
  }

  private void registerListeners() {
    for (ViewModelHorse horse : raceViewModel.getViewModelHorses())
    {
      horse.positionProperty().addListener((observable, oldValue, newValue) -> updateHorseLabels(horse.getName(), oldValue, newValue));
    }
  }

  //Pretty dirty...
  private void updateHorseLabels(String horseName, Number oldValue, Number newValue) {
    for (Label horseLabel : horsesAsLabels) {
      if(horseLabel.getText().equals(horseName))
      {
        int oldRow = GridPane.getRowIndex(horseLabel);
        Platform.runLater(() -> {
          gridRace.getChildren().remove(horseLabel);
          gridRace.add(horseLabel, (Integer) newValue,oldRow);
        });
      }
    }
  }

  private void initializeUIHorses() {
    ArrayList<Label> horses = new ArrayList<>();
    Label horse23 = new Label("2/3");
    Label horse4 = new Label("4");
    Label horse5 = new Label("5");
    Label horse6 = new Label("6");
    Label horse7 = new Label("7");
    Label horse8 = new Label("8");
    Label horse9 = new Label("9");
    Label horse10 = new Label("10");
    Label horse1112 = new Label("11/12");
    horses.add(horse23);
    horses.add(horse4);
    horses.add(horse5);
    horses.add(horse6);
    horses.add(horse7);
    horses.add(horse8);
    horses.add(horse9);
    horses.add(horse10);
    horses.add(horse1112);
    horsesAsLabels = horses;
  }

  @FXML
  private void onButtonStartRace()
  {
    raceViewModel.startRace();
  }

  @FXML
  private void onButtonViewResults()
  {
     viewHandler.openResultsView();
     raceViewModel.displayResults();
  }

}
