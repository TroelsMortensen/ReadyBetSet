package com.pastimegames.readysetbet.javafxclient.socketclient.Core;

import com.pastimegames.readysetbet.javafxclient.socketclient.Networking.Client;
import com.pastimegames.readysetbet.javafxclient.socketclient.Networking.SocketClient;

public class ClientFactory {

    private Client client;

    public Client getClient() {
        if (client == null) {
            client = new SocketClient(); //Swap here for other networking implementations
        }
        return client;
    }
}
