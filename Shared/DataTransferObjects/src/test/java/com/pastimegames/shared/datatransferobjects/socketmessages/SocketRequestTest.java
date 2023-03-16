package com.pastimegames.shared.datatransferobjects.socketmessages;

import com.pastimegames.shared.datatransferobjects.PlayerDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SocketRequestTest {

    @Test
    public void testGenericStuff(){
        PlayerDto playerDto = new PlayerDto("Troels", "#123456");
        SocketRequest request = new SocketRequest("JoinLobby", playerDto);
        PlayerDto content = request.content(PlayerDto.class);
        assertEquals(playerDto.playerName(), content.playerName());
        assertEquals(playerDto.colorCode(), content.colorCode());
    }
}