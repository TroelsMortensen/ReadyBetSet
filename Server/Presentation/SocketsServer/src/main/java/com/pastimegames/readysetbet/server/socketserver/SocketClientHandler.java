package com.pastimegames.readysetbet.server.socketserver;

import com.pastimegames.readysetbet.core.application.gamemanager.GameManager;
import com.pastimegames.readysetbet.core.domain.exceptions.DomainLogicException;
import com.pastimegames.shared.datatransferobjects.PlayerDto;
import com.pastimegames.shared.datatransferobjects.socketmessages.Request;
import com.pastimegames.shared.datatransferobjects.socketmessages.Response;

import java.io.*;
import java.net.Socket;

public class SocketClientHandler {

    private final ObjectInputStream input;
    private final ObjectOutputStream output;

    private String playerName;
    private final GameManager gameManager;

    public SocketClientHandler(Socket socket, GameManager gameManager) throws IOException {
        setupListeners();
        this.gameManager = gameManager;
        input = new ObjectInputStream(socket.getInputStream());
        output = new ObjectOutputStream(socket.getOutputStream());
    }

    private void setupListeners() {
        // TODO setup listeners her.
    }

    public void handleClient() {
        try {
            while (true) {
                Request request = (Request) input.readObject();
                try {

                    switch (request.getCommandType()) {
                        case "JoinLobby": {
                            joinLobby(request);
                            break;
                        }
                        case "LeaveLobby": {
                            leaveLobby(request);
                            break;
                        }
                    }
                } catch (DomainLogicException dlex) {
                    Response failure = Response.Failure("Failure", dlex.getMessage());
                    output.writeObject(failure);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void leaveLobby(Request request) throws IOException {
        PlayerDto playerDto = (PlayerDto) request.getContent();
        playerName = playerDto.playerName();
        gameManager.removePlayerFromLobby(playerName);
        writeBackSuccessResponse(Response.Success(request.getCommandType(), "Success"));
    }

    private void joinLobby(Request request) throws IOException {
        PlayerDto playerDto = (PlayerDto) request.getContent();
        playerName = playerDto.playerName();
        gameManager.joinPlayer(playerDto);
        writeBackSuccessResponse(Response.Success(request.getCommandType(), "Success"));
    }

    private void writeBackSuccessResponse(Response request) throws IOException {
        Response success = request;
        output.writeObject(success);
    }
}
