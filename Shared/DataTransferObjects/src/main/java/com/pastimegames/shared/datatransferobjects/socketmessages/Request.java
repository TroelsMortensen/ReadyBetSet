package com.pastimegames.shared.datatransferobjects.socketmessages;

import java.io.Serializable;

public class Request implements Serializable {

    private final String commandType;
    private final Object content;


    public Request(String commandType, Object content) {
        this.commandType = commandType;
        this.content = content;
    }

    public String getCommandType() {
        return commandType;
    }

    public Object getContent() {
        return content;
    }
}
