package com.pastimegames.readysetbet.server.presentation.javafxgui;

import com.pastimegames.readysetbet.core.application.gamemanager.GameManager;
import com.pastimegames.readysetbet.core.application.gamemanager.GameManagerImpl;
import com.pastimegames.readysetbet.core.domain.domainservices.DiceRoller;
import com.pastimegames.readysetbet.core.domain.entities.lobby.RaceOptions;
import com.pastimegames.readysetbet.server.infrastructure.javarandomdicerolling.JavaRandomDiceRoller;
import com.pastimegames.readysetbet.server.presentation.javafxgui.Core.ViewHandler;
import com.pastimegames.readysetbet.server.presentation.javafxgui.Model.Model;
import com.pastimegames.readysetbet.server.presentation.javafxgui.Core.ViewModelFactory;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class RunServer extends Application
{
  @Override public void start(Stage stage) throws IOException
  {

    //TESTING
    DiceRoller diceRoller = new JavaRandomDiceRoller();
    GameManager gm = new GameManagerImpl(diceRoller);
    gm.prepareForRacing(new RaceOptions().setMoveTickTimeInMs(500));
    gm.startRace();

    //FINAL
    Model model = new Model();
    ViewModelFactory viewModelFactory = new ViewModelFactory(model);
    ViewHandler viewHandler = new ViewHandler(viewModelFactory);
    viewHandler.start(stage);

/*    //DEBUG
    FXMLLoader fxmlLoaderForTest = new FXMLLoader(
        RunServer.class.getResource("TEST_ClientSimulator.fxml"));
    Scene testScene = null;
    try
    {
      testScene = new Scene(fxmlLoaderForTest.load(), 320, 240);
    }
    catch (IOException e)
    {
      throw new RuntimeException(e);
    }
    TESTClientSimulator testClientSimulator = fxmlLoaderForTest.getController();
    testClientSimulator.init(model);
    Stage testStage = new Stage();
    testStage.setTitle("TEST");
    testStage.setScene(testScene);
    testStage.show();*/
  }

  public static void main(String[] args)
  {
    launch();
  }
}