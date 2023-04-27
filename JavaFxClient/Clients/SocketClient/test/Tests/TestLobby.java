package Tests;

import DummyClasses.TestClient;
import DummyClasses.TestClientFactory;
import Utility.WaitForRunLater;
import com.pastimegames.readysetbet.javafxclient.socketclient.Core.ClientFactory;
import com.pastimegames.readysetbet.javafxclient.socketclient.Core.ViewModelFactory;
import com.pastimegames.readysetbet.javafxclient.socketclient.Model.Model;
import com.pastimegames.readysetbet.javafxclient.socketclient.Model.ModelManager;
import com.pastimegames.readysetbet.javafxclient.socketclient.ViewModel.JoinLobbyViewModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.embed.swing.JFXPanel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestLobby {

    private static ClientFactory clientFactory;
    private static JoinLobbyViewModel joinLobbyViewModel;

    @BeforeAll
    public static void setup() {
        clientFactory = new TestClientFactory();
        Model model = new ModelManager(clientFactory);
        ViewModelFactory viewModelFactory = new ViewModelFactory(model);
        new JFXPanel(); //This is needed to initialize JavaFX Toolkit
        joinLobbyViewModel = viewModelFactory.getJoinLobbyViewModel();
        joinLobbyViewModel.getNameProperty().set("NeoGeo");
        joinLobbyViewModel.getColourProperty().set("Red"); }

    @Test
    @Order(1)
    public void doesSystemResponseDisplay_JOINREQUESTACCEPTED_OnSuccesfullJoin()  {
        //arrange
        StringProperty testingResult = new SimpleStringProperty();
        testingResult.bind(joinLobbyViewModel.systemResponseProperty());

        //act
        joinLobbyViewModel.join();
        WaitForRunLater.waitForRunLater();
        String result = testingResult.getValue();

        //assert
        assertEquals("JOIN REQUEST ACCEPTED", result);
    }

    @Test
    @Order(2)
    public void doesViewModelSend_LOBBY_CLOSED_EventWhenLobbyIsFinalized()
    {
        //arrange
        boolean[] isLobbyFinalized = {false}; //wrapping in array to make it final for lambda
        joinLobbyViewModel.addPropertyChangeListener("LOBBY_CLOSED", (event) -> isLobbyFinalized[0] = true);
        TestClient client = (TestClient) clientFactory.getClient();

        //act
        client.lobbyFinalized();

        //assert
        assertTrue(isLobbyFinalized[0]);
    }



}
