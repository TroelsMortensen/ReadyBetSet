package com.pastimegames.readysetbet.javafxclient.socketclient.ViewModel;

import com.pastimegames.readysetbet.javafxclient.socketclient.Events.HorseMovedEvent;
import com.pastimegames.readysetbet.javafxclient.socketclient.Model.Model;
import com.pastimegames.readysetbet.javafxclient.socketclient.ViewModel.ModelRepresentations.HorseRepresentation;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;

public class RaceViewModel
{
  private final List<HorseRepresentation> horses;
  private final Model model;

  public RaceViewModel(Model model)
  {
    this.model = model;
    horses = initializeHorses();
    initializeListeners();
  }

  public List<HorseRepresentation> getViewModelHorses()
  {
    return horses;
  }

  private void initializeListeners() {
    model.addPropertyChangeListener("HORSE_MOVED", this::onHorseMoved);
  }

  private List<HorseRepresentation> initializeHorses() {
    ArrayList<HorseRepresentation> horses = new ArrayList<>();
    horses.add(new HorseRepresentation("2/3", 0));
    horses.add(new HorseRepresentation("4", 0));
    horses.add(new HorseRepresentation("5", 0));
    horses.add(new HorseRepresentation("6", 0));
    horses.add(new HorseRepresentation("7", 0));
    horses.add(new HorseRepresentation("8", 0));
    horses.add(new HorseRepresentation("9", 0));
    horses.add(new HorseRepresentation("10", 0));
    horses.add(new HorseRepresentation("11/12", 0));
    return horses;
  }

  private void onHorseMoved(PropertyChangeEvent propertyChangeEvent) {
    HorseMovedEvent horseMovedEvent = (HorseMovedEvent) propertyChangeEvent.getNewValue();
    for (HorseRepresentation horse : horses) {
      if(horse.getName().equals(horseMovedEvent.name()))
      {
        horse.setPosition(horseMovedEvent.position());
      }
    }
  }

}
