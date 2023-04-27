package DummyClasses;

import com.pastimegames.readysetbet.javafxclient.socketclient.Events.BetPlacedEvent;
import com.pastimegames.readysetbet.javafxclient.socketclient.Events.HorseMovedEvent;
import com.pastimegames.readysetbet.javafxclient.socketclient.Events.PlayerJoinedEvent;
import com.pastimegames.readysetbet.javafxclient.socketclient.Networking.Client;
import com.pastimegames.shared.datatransferobjects.HorseMovedDto;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class TestClient implements Client {

    private PropertyChangeSupport support = new PropertyChangeSupport(this);

    @Override
    public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        support.addPropertyChangeListener(propertyName, listener);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    @Override
    public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        support.removePropertyChangeListener(propertyName, listener);
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }

    @Override
    public void placeBet(int indexOfBet, int value, String playerName, String colour) {
        //instantly creates a mirrored BetPlacedEvent and fires it.
        //no serverside logic being tested
        BetPlacedEvent betPlacedEvent = new BetPlacedEvent(indexOfBet, value, playerName, colour);
        support.firePropertyChange("BET_PLACED", null, betPlacedEvent);
    }

    @Override
    public void joinLobby(String playerNameRequest, String colour) {
        //instantly creates a mirrored PlayerJoinedEvent and fires it.
        //no serverside logic being tested
        PlayerJoinedEvent playerJoinedLobbyEvent = new PlayerJoinedEvent(playerNameRequest, colour);
        support.firePropertyChange("PLAYER_JOINED", null, playerJoinedLobbyEvent);
    }

    @Override
    public void start() {
        //required from interface
        //needs no implementation since no server is being used
    }

    //can be called directly on TestClient to simulate horse movement.
    public void horsemoved(HorseMovedDto horseMovedDto) {
        HorseMovedEvent horseMovedEvent = new HorseMovedEvent(horseMovedDto.horseName(), horseMovedDto.position());
        support.firePropertyChange("HORSE_MOVED", null, horseMovedEvent);
    }

    //can be called directly on TestClient to simulate lobby finalization
    public void lobbyFinalized() {
        support.firePropertyChange("LOBBY_CLOSED", null, null);
    }

}