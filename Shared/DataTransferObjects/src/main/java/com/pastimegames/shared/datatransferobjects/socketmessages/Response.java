package com.pastimegames.shared.datatransferobjects.socketmessages;

import java.io.Serializable;

public class Response implements Serializable {

    private final String commandType;
    private final Object content;
    private boolean isSuccess;

    public Response(String commandType, Object content) {
        this.commandType = commandType;
        this.content = content;
    }

    public static Response Success(String responseType, Object content) {
        Response response = new Response(responseType, content);
        response.isSuccess = true;
        return response;
    }

    public static Response Failure(String responseType, Object content) {
        Response response = new Response(responseType, content);
        response.isSuccess = false;
        return response;
    }

    public Object getContent() {
        return content;
    }

    public String getCommandType() {
        return commandType;
    }

    public boolean isSuccess() {
        return isSuccess;
    }
}
