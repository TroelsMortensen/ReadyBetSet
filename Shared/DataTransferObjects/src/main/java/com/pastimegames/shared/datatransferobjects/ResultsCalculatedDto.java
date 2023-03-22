package com.pastimegames.shared.datatransferobjects;

import java.io.Serializable;
import java.util.Map;

public record ResultsCalculatedDto(Map<String, Integer> playerBalances) implements Serializable {
}
