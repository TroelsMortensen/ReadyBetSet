package com.pastimegames.readysetbet.server.socketserver;

import com.pastimegames.readysetbet.core.application.gamemanager.GameManager;
import com.pastimegames.readysetbet.core.domain.eventpublisher.DomainEventListener;
import com.pastimegames.readysetbet.core.domain.eventpublisher.DomainEventPublisher;
import com.pastimegames.readysetbet.core.domain.events.PlayerJoinedLobby;
import com.pastimegames.readysetbet.core.domain.exceptions.DomainLogicException;
import com.pastimegames.readysetbet.server.socketserver.handlers.LobbySocketHandler;
import com.pastimegames.readysetbet.server.socketserver.handlers.RaceSocketHandler;
import com.pastimegames.shared.datatransferobjects.socketmessages.SocketDto;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SocketClientHandler {

    private final ObjectInputStream input;
    private final ObjectOutputStream output;
    private final LobbySocketHandler lobbyHandler;
    private final RaceSocketHandler raceHandler;

    private String playerName;
//    private final GameManager gameManager;

    public SocketClientHandler(Socket socket, GameManager gameManager) throws IOException {
        setupListeners();
//        this.gameManager = gameManager;
        input = new ObjectInputStream(socket.getInputStream());
        output = new ObjectOutputStream(socket.getOutputStream());
        lobbyHandler = new LobbySocketHandler(gameManager);
        raceHandler = new RaceSocketHandler(gameManager, output);
    }

    private void setupListeners() {
        DomainEventPublisher.instance().subscribe(PlayerJoinedLobby.type(), (DomainEventListener<PlayerJoinedLobby>) evt -> {
            //output.writeObject();
        });
    }

    public void handleClient() {
        try {
            while (true) {
                SocketDto request = (SocketDto) input.readObject();
                String uri = request.commandType().toLowerCase();
                String handlerType = uri.split("/")[0];
                String actionType = uri.split("/")[1];

                try {
                    switch (handlerType) {
                        case "lobby": {
                            lobbyHandler.handle(actionType, request.content());
                            break;
                        }

                    }
                } catch (DomainLogicException dlex) {
                    output.writeObject(new SocketDto("ERROR", dlex.getMessage()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
