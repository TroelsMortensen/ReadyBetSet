package com.pastimegames.readysetbet.server.presentation.javafxgui.Core;

import com.pastimegames.readysetbet.server.presentation.javafxgui.Model.Model;
import com.pastimegames.readysetbet.server.presentation.javafxgui.ViewModel.RaceViewModel;
import com.pastimegames.readysetbet.server.presentation.javafxgui.ViewModel.ServerLobbyViewModel;

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
