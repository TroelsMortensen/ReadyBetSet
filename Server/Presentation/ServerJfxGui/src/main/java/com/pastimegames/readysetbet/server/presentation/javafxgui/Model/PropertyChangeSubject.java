package com.pastimegames.readysetbet.server.presentation.javafxgui.Model;

import java.beans.PropertyChangeListener;

public interface PropertyChangeSubject
{

    void addPropertyChangeListener(String propertyName, PropertyChangeListener listener);
    void addPropertyChangeListener(PropertyChangeListener listener);
    void removePropertyChangeListener(String propertyName, PropertyChangeListener listener);
    void removePropertyChangeListener(PropertyChangeListener listener);

}
