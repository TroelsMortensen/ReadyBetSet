package dummypackage;

import dummypackage.Core.ViewHandler;
import dummypackage.Core.ViewModelFactory;
import dummypackage.Model.Model;
import dummypackage.View.ServerMainViewController;
import dummypackage.ViewModel.ServerLobbyViewModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class RunServer extends Application
{
  @Override public void start(Stage stage) throws IOException
  {
    Model model = new Model();
    ViewModelFactory viewModelFactory = new ViewModelFactory(model);
    ViewHandler viewHandler = new ViewHandler(viewModelFactory);
    viewHandler.start(stage);

    //DEBUG
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
    testStage.show();
  }

  public static void main(String[] args)
  {
    launch();
  }
}