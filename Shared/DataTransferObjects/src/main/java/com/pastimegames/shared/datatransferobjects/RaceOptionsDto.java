package com.pastimegames.shared.datatransferobjects;

import java.io.Serializable;

public record RaceOptionsDto(int i, int moveTickTime, int numberOfAllowedBidsPerCell) implements Serializable {
}
