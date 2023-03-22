package com.pastimegames.shared.datatransferobjects.socketmessages;

public class SocketMessages {

    public static class Events {
        public static final String ERROR = "error";

        public static class Race {
            public static final String HORSE_MOVED = "horse_moved";
            public static final String RACE_FINISHED = "race_finished";
            public static final String RACE_STARTED = "race_started";
            public static final String GO_TO_RACE_VIEW = "go_to_race_view";
            public static final String NEXT_RACE_READY = "next_race_ready";
        }

        public static class Betting {
            public static final String BET_PLACED = "bet_placed";
            public static final String BETS_ARE_CLOSED = "bets_are_closed";
        }

        public static class Lobby {
            public static final String PLAYER_JOINED = "player_joined";
            public static final String PLAYER_LEFT = "player_left";
            public static final String LOBBY_FINALIZED = "lobby_finalized";
        }

    }

    public static class Commands {

        public static class Lobby {
            public static final String BASE_URL = "lobby";
            public static final String JOIN = BASE_URL + "/join";
            public static final String LEAVE = BASE_URL + "/leave";
        }

        public static class Betting {
            public static final String BASE_URL = "betting";
            public static final String BET_ON_CELL = BASE_URL + "/bet_on_cell";
            public static final String BET_ON_PROP_CARD = BASE_URL + "/bet_on_prop_card";
        }

        public static class Race {
            public static final String BASE_URL = "race";
        }
    }
}
