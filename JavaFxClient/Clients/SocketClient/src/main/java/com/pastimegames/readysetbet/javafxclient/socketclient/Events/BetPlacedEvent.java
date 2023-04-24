package com.pastimegames.readysetbet.javafxclient.socketclient.Events;

public record BetPlacedEvent(int index, int coinValue, String color, String owningPlayer)
{
}
