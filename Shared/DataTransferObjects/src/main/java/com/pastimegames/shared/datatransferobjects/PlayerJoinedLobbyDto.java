package com.pastimegames.shared.datatransferobjects;

import java.io.Serializable;

public record PlayerJoinedLobbyDto(String name, String color) implements Serializable {
}
