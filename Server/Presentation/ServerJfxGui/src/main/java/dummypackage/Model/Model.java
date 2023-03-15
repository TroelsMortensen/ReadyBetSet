package dummypackage.Model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Model implements PropertyChangeSubject
{
  private PropertyChangeSupport propertyChangeSupport;

  public Model()
  {
    propertyChangeSupport = new PropertyChangeSupport(this);
  }

  public void playerJoin(String name)
  {
    propertyChangeSupport.firePropertyChange("PLAYER_JOIN", null, name);
  }

  public void playerLeave(String name)
  {
    propertyChangeSupport.firePropertyChange("PLAYER_LEAVE", null, name);
  }

  @Override
  public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
    if(propertyName == null || propertyName.equals(""))
      addPropertyChangeListener(listener);
    else
      propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
  }

  @Override
  public void addPropertyChangeListener(PropertyChangeListener listener) {
    propertyChangeSupport.addPropertyChangeListener(listener);
  }

  @Override
  public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
    if(propertyName == null || propertyName.equals(""))
      removePropertyChangeListener(listener);
    else
      propertyChangeSupport.removePropertyChangeListener(propertyName, listener);
  }

  @Override
  public void removePropertyChangeListener(PropertyChangeListener listener) {
    propertyChangeSupport.removePropertyChangeListener(listener);
  }
}
