package Tests;

import DummyClasses.TestClient;
import DummyClasses.TestClientFactory;
import Utility.WaitForRunLater;
import com.pastimegames.readysetbet.javafxclient.socketclient.Core.ClientFactory;
import com.pastimegames.readysetbet.javafxclient.socketclient.Core.ViewModelFactory;
import com.pastimegames.readysetbet.javafxclient.socketclient.Model.Model;
import com.pastimegames.readysetbet.javafxclient.socketclient.Model.ModelManager;
import com.pastimegames.readysetbet.javafxclient.socketclient.ViewModel.BettingBoardViewModel;
import com.pastimegames.readysetbet.javafxclient.socketclient.ViewModel.JoinLobbyViewModel;
import javafx.embed.swing.JFXPanel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestBetting {

    private static ViewModelFactory viewModelFactory;
    private static ClientFactory clientFactory;
    private static TestClient client;

    @BeforeAll
    public static void setup() {
        clientFactory = new TestClientFactory();
        Model model = new ModelManager(clientFactory);
        viewModelFactory = new ViewModelFactory(model);
        new JFXPanel(); //This is needed to initialize JavaFX Toolkit
        JoinLobbyViewModel joinLobbyViewModel = viewModelFactory.getJoinLobbyViewModel();
        joinLobbyViewModel.getNameProperty().set("NeoGeo");
        joinLobbyViewModel.getColourProperty().set("Red");
        joinLobbyViewModel.join();
        WaitForRunLater.waitForRunLater();
        client = (TestClient) clientFactory.getClient();
        client.lobbyFinalized();
    }

    @Test
    public void doesViewModelSend_BET_PLACED_ON_INDEX_WhenABetIsPlacedOnIndex_21()
    {
        //arrange
        BettingBoardViewModel bettingBoardViewModel = viewModelFactory.getBettingBoardViewModel();
        final int[] betsPlaced = {0}; //wrapping in array to make it final for lambda
        bettingBoardViewModel.addPropertyChangeListener("BET_PLACED_ON_INDEX", (event) -> {
            betsPlaced[0] = (int) event.getNewValue();
        });

        //act
        client.placeBet(21, 4, "NeoGeo", "Red");

        //assert
        assertEquals(21, betsPlaced[0]);
    }

}
