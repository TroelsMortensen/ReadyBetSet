package com.pastimegames.shared.datatransferobjects;

import java.io.Serializable;

public record PlayerDto(String playerName, String colorCode) implements Serializable {
}
