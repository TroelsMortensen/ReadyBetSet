package dummypackage;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class DummyClass extends Application
{
  @Override public void start(Stage stage) throws IOException
  {
    FXMLLoader fxmlLoader = new FXMLLoader(
        DummyClass.class.getResource("ServerMainView.fxml"));
    Scene scene = null;
    try
    {
      scene = new Scene(fxmlLoader.load(), 320, 240);
    }
    catch (IOException e)
    {
      throw new RuntimeException(e);
    }
    stage.setTitle("Hello!");
    stage.setScene(scene);
    stage.show();
  }

  public static void main(String[] args)
  {
    launch();
  }
}