package com.pastimegames.shared.datatransferobjects;

import java.io.Serializable;

public record CoinDto(int value, String playerName, String color) implements Serializable {
}
