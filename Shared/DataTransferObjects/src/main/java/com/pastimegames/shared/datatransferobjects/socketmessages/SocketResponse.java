package com.pastimegames.shared.datatransferobjects.socketmessages;

import com.google.gson.Gson;

public class SocketResponse {
    private String responseType;
    private String content;
    private boolean isSuccess;

    public static SocketResponse Success(String responseType, Object content) {
        SocketResponse response = new SocketResponse(responseType, content);
        response.isSuccess = true;
        return response;
    }

    public static SocketResponse Failure(String responseType, Object content) {
        SocketResponse response = new SocketResponse(responseType, content);
        response.isSuccess = false;
        return response;
    }

    private SocketResponse(String responseType, Object content) {
        this.responseType = responseType;
        this.content = new Gson().toJson(content);
    }

    public String responseType() {
        return responseType;
    }

    public <T> T content(Class<T> type) {
        Gson gson = new Gson();
        return gson.fromJson(content, type);
    }

    public boolean isSuccess() {
        return isSuccess;
    }
}
