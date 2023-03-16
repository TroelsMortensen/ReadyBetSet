package com.pastimegames.readysetbet.javafxclient.socketclient;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import com.pastimegames.shared.datatransferobjects.socketmessages.Request;
import com.pastimegames.shared.datatransferobjects.PlayerDto;

public class SocketClient {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 2910);
        ClientConnection clientConnection = new ClientConnection(socket);
        new Thread(clientConnection).start();

        while(true) {
            Scanner keyboard = new Scanner(System.in);
            String stringToSend = keyboard.nextLine();
            if(stringToSend.equals("join"))
            {
                Request joinLobbyRequest = new Request("JoinLobby", new PlayerDto("NeoGeo", "purple"));
                clientConnection.sendRequest(joinLobbyRequest);
            }
            if(stringToSend.equals("leave"))
            {
                Request leaveLobbyRequest = new Request("LeaveLobby", new PlayerDto("NeoGeo", "purple"));
                clientConnection.sendRequest(leaveLobbyRequest);
            }
        }
    }
}