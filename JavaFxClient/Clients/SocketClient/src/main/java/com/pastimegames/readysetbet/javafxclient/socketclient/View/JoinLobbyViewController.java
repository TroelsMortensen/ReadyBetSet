package com.pastimegames.readysetbet.javafxclient.socketclient.View;

import com.pastimegames.readysetbet.javafxclient.socketclient.Core.ViewHandler;
import com.pastimegames.readysetbet.javafxclient.socketclient.ViewModel.JoinLobbyViewModel;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class JoinLobbyViewController {
    @FXML
    private ComboBox<String> comboBoxPlayerColour;
    @FXML
    private TextField textFieldPlayerName;
    @FXML
    private Label labelSystemResponse;
    @FXML
    private Button buttonJoinLobby;

    private ViewHandler viewHandler;
    private JoinLobbyViewModel joinLobbyViewModel;

    public void init(ViewHandler viewHandler, JoinLobbyViewModel joinLobbyViewModel) {
        this.viewHandler = viewHandler;
        this.joinLobbyViewModel = joinLobbyViewModel;
        initializeColours();

        textFieldPlayerName.textProperty().bindBidirectional(joinLobbyViewModel.getNameProperty());
        comboBoxPlayerColour.setOnAction(event -> {
            String pickedColour = comboBoxPlayerColour.getValue();
            joinLobbyViewModel.getColourProperty().set(pickedColour);
        });
        labelSystemResponse.textProperty().bind(joinLobbyViewModel.systemResponseProperty());
        textFieldPlayerName.disableProperty().bind(joinLobbyViewModel.joinRequestAcceptedProperty());
        comboBoxPlayerColour.disableProperty().bind(joinLobbyViewModel.joinRequestAcceptedProperty());
        buttonJoinLobby.disableProperty().bind(joinLobbyViewModel.joinRequestAcceptedProperty());
        joinLobbyViewModel.setOnLobbyClosed(this::onLobbyClosed);
    }

    private void onLobbyClosed(Object o) {
        System.out.println("Lobby closed in view");
        Platform.runLater(() -> viewHandler.openViewBetting());
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
