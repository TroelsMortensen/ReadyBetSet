package com.pastimegames.readysetbet.javafxclient.socketclient.Model;

import java.beans.PropertyChangeListener;

public interface PropertyChangeSubject
{

    void addPropertyChangeListener(String propertyName, PropertyChangeListener listener);
    void addPropertyChangeListener(PropertyChangeListener listener);
    void removePropertyChangeListener(String propertyName, PropertyChangeListener listener);
    void removePropertyChangeListener(PropertyChangeListener listener);

}
