package com.pastimegames.readysetbet.core.application.gamemanager;

public class GameState {
    public enum State{
        IN_LOBBY,
        RACE_READY,
        RACE_IN_PROGRESS,
        RACE_FINISHED,
        IN_RESULTS
    }

    private State currentGameState;

    public void updateState(State state){
        System.out.println("Changing game state to " + state.name());
        currentGameState = state;
    }

    public State currentGameState(){
        return currentGameState;
    }

}
