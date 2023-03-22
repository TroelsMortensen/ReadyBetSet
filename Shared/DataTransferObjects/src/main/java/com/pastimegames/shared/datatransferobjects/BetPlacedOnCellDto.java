package com.pastimegames.shared.datatransferobjects;

import java.io.Serializable;

public record BetPlacedOnCellDto(int index, int coinValue, String owningPlayer, String color) implements Serializable {
}
