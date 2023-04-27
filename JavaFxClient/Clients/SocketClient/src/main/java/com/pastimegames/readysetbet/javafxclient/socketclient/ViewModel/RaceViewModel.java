package com.pastimegames.readysetbet.javafxclient.socketclient.ViewModel;

import com.pastimegames.readysetbet.javafxclient.socketclient.Events.HorseMovedEvent;
import com.pastimegames.readysetbet.javafxclient.socketclient.Model.Model;
import com.pastimegames.readysetbet.javafxclient.socketclient.Model.PropertyChangeSubject;
import com.pastimegames.readysetbet.javafxclient.socketclient.ViewModel.ModelRepresentations.HorseRepresentation;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class RaceViewModel implements PropertyChangeSubject
{
  private final List<HorseRepresentation> horses;
  private final Model model;

  private PropertyChangeSupport support;

  public RaceViewModel(Model model)
  {
    this.model = model;
    support = new PropertyChangeSupport(this);

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

  /*
    PropertyChangeSubject interface implementation
     */
  @Override
  public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
    if (propertyName == null || propertyName.equals(""))
      addPropertyChangeListener(listener);
    else
      support.addPropertyChangeListener(propertyName, listener);
  }

  @Override
  public void addPropertyChangeListener(PropertyChangeListener listener) {
    support.addPropertyChangeListener(listener);
  }

  @Override
  public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
    if (propertyName == null || propertyName.equals(""))
      removePropertyChangeListener(listener);
    else
      support.removePropertyChangeListener(propertyName, listener);
  }

  @Override
  public void removePropertyChangeListener(PropertyChangeListener listener) {
    support.removePropertyChangeListener(listener);
  }

}
