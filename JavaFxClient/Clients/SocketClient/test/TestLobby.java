import com.pastimegames.readysetbet.javafxclient.socketclient.Core.ClientFactory;
import com.pastimegames.readysetbet.javafxclient.socketclient.Core.ViewModelFactory;
import com.pastimegames.readysetbet.javafxclient.socketclient.Events.BetPlacedEvent;
import com.pastimegames.readysetbet.javafxclient.socketclient.Events.HorseMovedEvent;
import com.pastimegames.readysetbet.javafxclient.socketclient.Events.PlayerJoinedEvent;
import com.pastimegames.readysetbet.javafxclient.socketclient.Model.Model;
import com.pastimegames.readysetbet.javafxclient.socketclient.Model.ModelManager;
import com.pastimegames.readysetbet.javafxclient.socketclient.Networking.Client;
import com.pastimegames.readysetbet.javafxclient.socketclient.ViewModel.BettingBoardViewModel;
import com.pastimegames.readysetbet.javafxclient.socketclient.ViewModel.JoinLobbyViewModel;
import com.pastimegames.shared.datatransferobjects.HorseMovedDto;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.concurrent.Semaphore;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestLobby {

    private static ViewModelFactory viewModelFactory;
    private static ClientFactory clientFactory;

    @BeforeAll
    public static void setup() {
        clientFactory = new TestClientFactory();
        Model model = new ModelManager(clientFactory);
        viewModelFactory = new ViewModelFactory(model);
        new JFXPanel(); //This is needed to initialize JavaFX Toolkit
    }

    @Test
    public void testLoginWithNameNeoGeo()  {
        //arrange
        JoinLobbyViewModel joinLobbyViewModel = viewModelFactory.getJoinLobbyViewModel();
        joinLobbyViewModel.getNameProperty().set("NeoGeo");
        joinLobbyViewModel.getColourProperty().set("Red");

        //act
        joinLobbyViewModel.join();
        waitForRunLater();
        String result = joinLobbyViewModel.systemResponseProperty().getValue();

        //assert
        assertEquals("JOIN REQUEST ACCEPTED", result);
    }

    @Test
    public void testLoginWithNameNeoGeoAndLobbyFinalizing()
    {
        //arrange
        JoinLobbyViewModel joinLobbyViewModel = viewModelFactory.getJoinLobbyViewModel();
        joinLobbyViewModel.getNameProperty().set("NeoGeo");
        joinLobbyViewModel.getColourProperty().set("Red");

        //act
        boolean[] isLobbyFinalized = {false}; //wrapping in array to make it final for lambda
        joinLobbyViewModel.addPropertyChangeListener("LOBBY_CLOSED", (event) -> {
            isLobbyFinalized[0] = true;
        });
        joinLobbyViewModel.join();
        waitForRunLater();

        TestClient client = (TestClient) clientFactory.getClient();
        client.lobbyFinalized();
        waitForRunLater();


        assertEquals(true, isLobbyFinalized[0]);
    }

    @Test
    public void testDoesReceiveInformationWhenAnotherPlayerPlacesABet()
    {
        //arrange
        JoinLobbyViewModel joinLobbyViewModel = viewModelFactory.getJoinLobbyViewModel();
        joinLobbyViewModel.getNameProperty().set("NeoGeo");
        joinLobbyViewModel.getColourProperty().set("Red");
        joinLobbyViewModel.join();
        waitForRunLater();
        TestClient client = (TestClient) clientFactory.getClient();
        client.lobbyFinalized();

        BettingBoardViewModel bettingBoardViewModel = viewModelFactory.getBettingBoardViewModel();
        final int[] betsPlaced = {0}; //wrapping in array to make it final for lambda
        bettingBoardViewModel.addPropertyChangeListener("BET_PLACED_ON_INDEX", (event) -> {
            betsPlaced[0] = (int) event.getNewValue();
        });

        client.placeBet(21, 4, "NeoGeo", "Red");

        //assert
        assertEquals(21, betsPlaced[0]);
    }

    public static void waitForRunLater() {
        Semaphore semaphore = new Semaphore(0);
        Platform.runLater(() -> semaphore.release());
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private static class TestClientFactory extends ClientFactory {

        private TestClient client;

        @Override
        public Client getClient() {
            if(client == null) {
                client = new TestClient();
            }
            return client;
        }
    }

    private static class TestClient implements Client {

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
}
