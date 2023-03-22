package com.pastimegames.readysetbet.javafxclient.socketclient.Core;

import com.pastimegames.readysetbet.javafxclient.socketclient.Model.Model;
import com.pastimegames.readysetbet.javafxclient.socketclient.ViewModel.BettingBoardViewModel;
import com.pastimegames.readysetbet.javafxclient.socketclient.ViewModel.JoinLobbyViewModel;
import com.pastimegames.readysetbet.javafxclient.socketclient.ViewModel.RaceViewModel;

public class ViewModelFactory
{
  private Model model;
  private RaceViewModel raceViewModel;
  private JoinLobbyViewModel joinLobbyViewModel;
  private BettingBoardViewModel bettingBoardViewModel;

  public ViewModelFactory(Model model)
  {
    this.model = model;
  }

  public JoinLobbyViewModel getJoinLobbyViewModel()
  {
    if(joinLobbyViewModel == null)
    {
      joinLobbyViewModel = new JoinLobbyViewModel(model);
    }
    return joinLobbyViewModel;
  }

  public RaceViewModel getRaceViewModel()
  {
    if(raceViewModel == null)
    {
      raceViewModel = new RaceViewModel(model);
    }
    return raceViewModel;
  }

  public BettingBoardViewModel getBettingBoardViewModel()
  {
    if(bettingBoardViewModel == null)
    {
      bettingBoardViewModel = new BettingBoardViewModel(model);
    }
    return bettingBoardViewModel;
  }


}
