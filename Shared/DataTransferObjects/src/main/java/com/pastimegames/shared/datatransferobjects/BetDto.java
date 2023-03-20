package com.pastimegames.shared.datatransferobjects;

import java.io.Serializable;

public record BetDto(int betPosition, CoinDto coin) implements Serializable {
}
