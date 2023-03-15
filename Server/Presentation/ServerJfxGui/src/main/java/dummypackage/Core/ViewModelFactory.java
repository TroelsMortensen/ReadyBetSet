package dummypackage.Core;

import dummypackage.Model.Model;
import dummypackage.ViewModel.RaceViewModel;
import dummypackage.ViewModel.ServerLobbyViewModel;

public class ViewModelFactory
{
  private Model model;
  private ServerLobbyViewModel serverLobbyViewModel;
  private RaceViewModel raceViewModel;

  public ViewModelFactory(Model model)
  {
    this.model = model;
  }

  public ServerLobbyViewModel getServerLobbyViewModel()
  {
    if(serverLobbyViewModel == null)
    {
      serverLobbyViewModel = new ServerLobbyViewModel(model);
    }
    return serverLobbyViewModel;
  }

  public RaceViewModel getRaceViewModel()
  {
    if(raceViewModel == null)
    {
      raceViewModel = new RaceViewModel(model);
    }
    return raceViewModel;
  }
}
