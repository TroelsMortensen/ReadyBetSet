package com.pastimegames.readysetbet.javafxclient.socketclient.View;

import com.pastimegames.readysetbet.javafxclient.socketclient.Core.ViewHandler;
import com.pastimegames.readysetbet.javafxclient.socketclient.ViewModel.JoinLobbyViewModel;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class JoinLobbyViewController {
    @FXML
    private ComboBox<String> comboBoxPlayerColour;
    @FXML
    private TextField textFieldPlayerName;

    private ViewHandler viewHandler;
    private JoinLobbyViewModel joinLobbyViewModel;

    public void init(ViewHandler viewHandler, JoinLobbyViewModel joinLobbyViewModel) {
        this.viewHandler = viewHandler;
        this.joinLobbyViewModel = joinLobbyViewModel;
        joinLobbyViewModel.setViewHandler(viewHandler);
        initializeColours();

        textFieldPlayerName.textProperty().bindBidirectional(joinLobbyViewModel.getName());
        comboBoxPlayerColour.setOnAction(event -> {
            String pickedColour = comboBoxPlayerColour.getValue();
            joinLobbyViewModel.getColour().set(pickedColour);
        });
    }

    private void initializeColours() {
        ObservableList<String> items = comboBoxPlayerColour.getItems();
        items.add("PURPLE");
        items.add("BLACK");
        items.add("GREY");
        items.add("RED");
        items.add("BLUE");
    }

    @FXML
    private void onButtonJoinLobby()
    {
        joinLobbyViewModel.join();
    }
}
