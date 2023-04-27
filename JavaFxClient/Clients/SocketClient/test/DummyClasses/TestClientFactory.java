package DummyClasses;

import com.pastimegames.readysetbet.javafxclient.socketclient.Core.ClientFactory;
import com.pastimegames.readysetbet.javafxclient.socketclient.Networking.Client;

public class TestClientFactory extends ClientFactory {

    private TestClient client;

    @Override
    public Client getClient() {
        if(client == null) {
            client = new TestClient();
        }
        return client;
    }
}