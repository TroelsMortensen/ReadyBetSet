package com.pastimegames.shared.datatransferobjects.socketmessages;

import java.io.Serializable;

public class SocketDto implements Serializable {

    private final String commandUri;
    private final Object content;


    public SocketDto(String commandUri, Object content) {
        this.commandUri = commandUri;
        this.content = content;
    }

    public String commandType() {
        return commandUri;
    }

    public Object content() {
        return content;
    }
}
