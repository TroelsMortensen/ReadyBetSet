package com.pastimegames.readysetbet.server.socketserver.handlers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SocketHandlerBaseTest {


    @Test
    public void testTypeMethod() {
        SocketHandlerBase base = new LobbySocketHandler(null, null);
        System.out.println(base.type());
    }
}