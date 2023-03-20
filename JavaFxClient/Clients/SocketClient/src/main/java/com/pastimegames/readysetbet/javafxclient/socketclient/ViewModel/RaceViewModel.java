package com.pastimegames.readysetbet.javafxclient.socketclient.ViewModel;

import com.pastimegames.readysetbet.javafxclient.socketclient.Events.HorseMovedEvent;
import com.pastimegames.readysetbet.javafxclient.socketclient.Model.Model;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;

public class RaceViewModel
{
  private List<ViewModelHorse> horses;
  private Model model;

  public RaceViewModel(Model model)
  {
    this.model = model;
    horses = initializeHorses();
    initializeListeners();
  }

  public List<ViewModelHorse> getViewModelHorses()
  {
    return horses;
  }

  private void initializeListeners() {
    model.addPropertyChangeListener("HORSE_MOVED", this::onHorseMoved);
  }

  private List<ViewModelHorse> initializeHorses() {
    ArrayList<ViewModelHorse> horses = new ArrayList<>();
    horses.add(new ViewModelHorse("2/3", 0));
    horses.add(new ViewModelHorse("4", 0));
    horses.add(new ViewModelHorse("5", 0));
    horses.add(new ViewModelHorse("6", 0));
    horses.add(new ViewModelHorse("7", 0));
    horses.add(new ViewModelHorse("8", 0));
    horses.add(new ViewModelHorse("9", 0));
    horses.add(new ViewModelHorse("10", 0));
    horses.add(new ViewModelHorse("11/12", 0));
    return horses;
  }

  private void onHorseMoved(PropertyChangeEvent propertyChangeEvent) {
    HorseMovedEvent horseMovedEvent = (HorseMovedEvent) propertyChangeEvent.getNewValue();
    for (ViewModelHorse horse : horses) {
      if(horse.getName().equals(horseMovedEvent.name()))
      {
        horse.setPosition(horseMovedEvent.position());
      }
    }
  }

}
