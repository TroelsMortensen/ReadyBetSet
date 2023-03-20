package com.pastimegames.readysetbet.javafxclient.socketclient.Networking;

import com.pastimegames.readysetbet.javafxclient.socketclient.Model.PropertyChangeSubject;
import com.pastimegames.shared.datatransferobjects.socketmessages.SocketDto;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.*;
import java.net.Socket;

public class ClientConnection implements Runnable, PropertyChangeSubject {


    private ObjectInputStream inFromServer;
    private ObjectOutputStream outToServer;
    private PropertyChangeSupport propertyChangeSupport;

    public ClientConnection(Socket serverSocket) {
        propertyChangeSupport = new PropertyChangeSupport(this);
        try {
            outToServer = new ObjectOutputStream(serverSocket.getOutputStream());
            inFromServer = new ObjectInputStream(serverSocket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
            try {
                while(true) {
                    SocketDto response = (SocketDto) inFromServer.readObject();
                    System.out.println("Received from server: " + response.content());
                    switch (response.commandType())
                    {
                        case "gotoraceview" :
                            gotoraceview();
                            break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
    }

    public void sendSocketDto(SocketDto socketDto)
    {
        try {
            outToServer.writeObject(socketDto);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void gotoraceview() {
        propertyChangeSupport.firePropertyChange("LOBBY_CLOSED", null, null);
    }

    public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        if(propertyName == null || propertyName.equals(""))
            addPropertyChangeListener(listener);
        else
            propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        if(propertyName == null || propertyName.equals(""))
            removePropertyChangeListener(listener);
        else
            propertyChangeSupport.removePropertyChangeListener(propertyName, listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

}
