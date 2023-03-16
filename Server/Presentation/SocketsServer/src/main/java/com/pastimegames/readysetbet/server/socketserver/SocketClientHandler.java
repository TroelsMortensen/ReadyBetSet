package com.pastimegames.readysetbet.server.socketserver;

import com.google.gson.Gson;
import com.pastimegames.readysetbet.core.application.gamemanager.GameManager;
import com.pastimegames.shared.datatransferobjects.PlayerDto;
import com.pastimegames.shared.datatransferobjects.socketmessages.SocketRequest;
import com.pastimegames.shared.datatransferobjects.socketmessages.SocketResponse;

import java.io.*;
import java.net.Socket;

public class SocketClientHandler {

    private final BufferedWriter output;
    private final BufferedReader input;

    private String playerName;
    private final GameManager gameManager;

    public SocketClientHandler(Socket socket, GameManager gameManager) throws IOException {
        setupListeners();
        this.gameManager = gameManager;
        input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
    }

    private void setupListeners() {

    }

    public void handleClient() {
        try {
            while (true) {
                String s = input.readLine();
                SocketRequest request = new Gson().fromJson(s, SocketRequest.class);
                switch (request.commandType()) {
                    case "JoinLobby": {
                        joinLobby(request);
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void joinLobby(SocketRequest request) throws IOException {
        PlayerDto playerDto = request.content(PlayerDto.class);
        playerName = playerDto.playerName();
        try {
            gameManager.joinPlayer(playerDto);
            SocketResponse success = SocketResponse.Success("JoinLobby", "Success");
            output.write(new Gson().toJson(success));
        } catch (Exception e) {
            String response = new Gson().toJson(SocketResponse.Failure("JoinLobby", e.getMessage()));
            output.write(response);
        }
    }
}
