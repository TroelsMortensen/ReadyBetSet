package com.pastimegames.shared.datatransferobjects.socketmessages;

public class SocketMessages
{

  public static class Events
  {
    public static final String ERROR = "error";

    public static class Race
    {
      public static final String HORSE_MOVED = "horse_moved";
      public static final String RACE_FINISHED = "horse_moved";
    }

    public static class Betting
    {
      public static final String BET_PLACED = "bet_placed";
      public static final String BETS_ARE_CLOSED = "bets_are_closed";
    }

    public static class Lobby
    {
      public static final String PLAYER_JOINED = "player_joined";
      public static final String PLAYER_LEFT = "player_left";
      public static final String LOBBY_FINALIZED = "lobby_finalized";
    }

  }

  public static class Commands
  {

    public static class Lobby
    {
      public static final String JOIN = "lobby/join";
      public static final String LEAVE = "lobby/leave";
    }
  }
}