package com.pastimegames.readysetbet.server.socketserver;

import com.pastimegames.readysetbet.core.application.gamemanager.GameManager;
import com.pastimegames.readysetbet.core.domain.exceptions.DomainLogicException;
import com.pastimegames.readysetbet.core.domain.exceptions.GameLogicException;
import com.pastimegames.readysetbet.server.socketserver.handlers.BettingSocketHandler;
import com.pastimegames.readysetbet.server.socketserver.handlers.LobbySocketHandler;
import com.pastimegames.readysetbet.server.socketserver.handlers.RaceSocketHandler;
import com.pastimegames.readysetbet.server.socketserver.handlers.SocketHandlerBase;
import com.pastimegames.shared.datatransferobjects.socketmessages.SocketDto;
import com.pastimegames.shared.datatransferobjects.socketmessages.SocketMessages;

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

        addHandler(new LobbySocketHandler(gameManager, this::writeToClient));
        addHandler(new RaceSocketHandler(gameManager, this::writeToClient));
        addHandler(new BettingSocketHandler(gameManager, this::writeToClient));
    }

    private void addHandler(SocketHandlerBase handler) {
        handlers.put(handler.type(), handler);
    }

    public void handleClient() {
        try {
            while (true) {
                SocketDto request = (SocketDto) input.readObject();
                String uri = request.commandType().toLowerCase();
                String[] handlerAndCommand = uri.split("/");
                String handlerType = handlerAndCommand[0];

                try {
                    if (!handlers.containsKey(handlerType)) {
                        output.writeObject(new SocketDto(SocketMessages.Events.ERROR, constructHandlerNotFoundErrorMessage(handlerType)));
                        continue;
                    }
                    handlers.get(handlerType).handle(uri, request.content());
                } catch (DomainLogicException | GameLogicException dlex) {
                    output.writeObject(new SocketDto(SocketMessages.Events.ERROR, dlex.getMessage()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String constructHandlerNotFoundErrorMessage(String handlerType) {
        StringBuilder sb = new StringBuilder();
        sb.append("No handler registered as '").append(handlerType).append("'. Available handlers are:\n");
        for (String s : handlers.keySet()) {
            sb.append(s).append("\n");
        }
        return sb.toString();
    }


    private void writeToClient(SocketDto dto) {
        try {
            synchronized (output) {
                output.writeObject(dto);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
