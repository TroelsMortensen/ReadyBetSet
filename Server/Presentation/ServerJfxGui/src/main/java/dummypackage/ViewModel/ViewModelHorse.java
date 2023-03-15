package dummypackage.ViewModel;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ViewModelHorse
{
  private StringProperty name;
  private IntegerProperty position;

  public ViewModelHorse()
  {
    name = new SimpleStringProperty();
    position = new SimpleIntegerProperty();
  }

  public String getName()
  {
    return name.get();
  }

  public StringProperty nameProperty()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name.set(name);
  }

  public int getPosition()
  {
    return position.get();
  }

  public IntegerProperty positionProperty()
  {
    return position;
  }

  public void setPosition(int position)
  {
    this.position.set(position);
  }
}
