package com.pastimegames.shared.datatransferobjects;

import java.io.Serializable;

public record HorseMovedDto(String horseName, int position) implements Serializable {
}
