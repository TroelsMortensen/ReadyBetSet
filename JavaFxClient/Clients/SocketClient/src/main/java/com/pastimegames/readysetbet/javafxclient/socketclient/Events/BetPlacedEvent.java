package com.pastimegames.readysetbet.javafxclient.socketclient.Events;

public record BetPlacedEvent(int index, int coinValue, String owningPlayer, String color)
{
}
