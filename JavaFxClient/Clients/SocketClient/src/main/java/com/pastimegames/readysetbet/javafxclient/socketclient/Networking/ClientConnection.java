package com.pastimegames.readysetbet.javafxclient.socketclient.Networking;

import com.pastimegames.readysetbet.javafxclient.socketclient.ViewModel.ViewModelPlayer;
import com.pastimegames.shared.datatransferobjects.PlayerDto;
import com.pastimegames.shared.datatransferobjects.socketmessages.SocketDto;

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
                    SocketDto response = (SocketDto) inFromServer.readObject();
                    System.out.println("Received from server: " + response.content());
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
    }

    public void joinLobby(ViewModelPlayer player) {
        SocketDto joinLobbyRequest = new SocketDto("Lobby/join", new PlayerDto(player.getName(), player.getColour()));
        sendSocketDto(joinLobbyRequest);
    }

    private void sendSocketDto(SocketDto socketDto)
    {
        try {
            outToServer.writeObject(socketDto);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
