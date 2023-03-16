package com.pastimegames.readysetbet.server.socketserver;

import com.pastimegames.readysetbet.core.application.gamemanager.GameManager;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {

    private final GameManager gameManager;

    public SocketServer(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public void runServer() {
        try (ServerSocket serverSocket = new ServerSocket(2910)) {
            System.out.println("Server started");
            System.out.println("Server ip:" + InetAddress.getLocalHost().getHostAddress());
            listenForClients(serverSocket);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private void listenForClients(ServerSocket serverSocket) throws IOException {
        while (true) {
            Socket socketToClient = serverSocket.accept();
            System.out.println("Client connected");
            SocketClientHandler ch = new SocketClientHandler(socketToClient, gameManager);
            Thread t = new Thread(ch::handleClient);
            t.setDaemon(true);
            t.start();
        }
    }
}
