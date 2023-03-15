package dummypackage;

import dummypackage.Model.Model;
import javafx.event.ActionEvent;

public class TESTClientSimulator
{
  private Model model;
  public void player1Join(ActionEvent actionEvent)
  {
    model.playerJoin("Player 1");
  }

  public void player2Join(ActionEvent actionEvent)
  {
    model.playerJoin("Player 2");
  }

  public void player3Join(ActionEvent actionEvent)
  {
    model.playerJoin("Player 3");
  }

  public void init(Model model)
  {
    this.model = model;
  }

  public void player1Leave(ActionEvent actionEvent)
  {
    model.playerLeave("Player 1");
  }

  public void player2Leave(ActionEvent actionEvent)
  {
    model.playerLeave("Player 2");
  }

  public void player3Leave(ActionEvent actionEvent)
  {
    model.playerLeave("Player 3");
  }
}
