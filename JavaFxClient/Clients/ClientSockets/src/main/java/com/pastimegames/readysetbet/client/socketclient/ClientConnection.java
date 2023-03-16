package com.pastimegames.readysetbet.client.socketclient;

import com.pastimegames.shared.datatransferobjects.socketmessages.Request;
import com.pastimegames.shared.datatransferobjects.socketmessages.Response;

import java.io.*;
import java.net.Socket;

public class ClientConnection implements Runnable{


    private ObjectInputStream inFromServer;
    private ObjectOutputStream outToServer;
    private Socket serverSocket;

    public ClientConnection(Socket serverSocket) {
        this.serverSocket = serverSocket;
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
                    Response response = (Response) inFromServer.readObject();
                    System.out.println("Received from server: " + response.isSuccess());
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
    }

    public void sendRequest(Request request)
    {
        try {
            outToServer.writeObject(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
