package com.pastimegames.readysetbet.server.socketserver;

import com.pastimegames.readysetbet.core.application.gamemanager.GameManager;
import com.pastimegames.readysetbet.core.domain.exceptions.DomainLogicException;
import com.pastimegames.readysetbet.core.domain.exceptions.GameLogicException;
import com.pastimegames.readysetbet.server.socketserver.handlers.BettingSocketHandler;
import com.pastimegames.readysetbet.server.socketserver.handlers.LobbySocketHandler;
import com.pastimegames.readysetbet.server.socketserver.handlers.RaceSocketHandler;
import com.pastimegames.readysetbet.server.socketserver.handlers.SocketHandlerBase;
import com.pastimegames.shared.datatransferobjects.socketmessages.SocketDto;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class SocketClientHandler {

    private final ObjectInputStream input;
    private final ObjectOutputStream output;

    private Map<String, SocketHandlerBase> handlers = new HashMap<>();
    private String playerName;

    public SocketClientHandler(Socket socket, GameManager gameManager) throws IOException {
        input = new ObjectInputStream(socket.getInputStream());
        output = new ObjectOutputStream(socket.getOutputStream());
        handlers.put("lobby",new LobbySocketHandler(gameManager));
        handlers.put("race",new RaceSocketHandler(gameManager, output));
        handlers.put("betting", new BettingSocketHandler(gameManager, output));
    }

    public void handleClient() {
        try {
            while (true) {
                SocketDto request = (SocketDto) input.readObject();
                String uri = request.commandType().toLowerCase();
                String[] handlerAndCommand = uri.split("/");
                String handlerType = handlerAndCommand[0];
                String commandType = handlerAndCommand[1];

                try {
                    handlers.get(handlerType).handle(commandType, request.content());
                } catch (DomainLogicException | GameLogicException dlex) {
                    output.writeObject(new SocketDto("ERROR", dlex.getMessage()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
