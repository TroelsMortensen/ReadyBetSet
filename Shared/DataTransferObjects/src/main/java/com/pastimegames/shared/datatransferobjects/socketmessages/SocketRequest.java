package com.pastimegames.shared.datatransferobjects.socketmessages;

import com.google.gson.Gson;

public class SocketRequest {

    private final String commandType;
    private final String content;

    public SocketRequest(String commandType, Object content) {
        this.commandType = commandType;
        this.content = new Gson().toJson(content);
    }

    public String commandType() {
        return commandType;
    }

    public <T> T content(Class<T> type) {
        Gson gson = new Gson();
        return gson.fromJson(content, type);
    }

}
