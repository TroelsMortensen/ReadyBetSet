package com.pastimegames.readysetbet.javafxclient.socketclient.Core;

import com.pastimegames.readysetbet.javafxclient.socketclient.Model.Model;
import com.pastimegames.readysetbet.javafxclient.socketclient.ViewModel.BettingBoardViewModel;
import com.pastimegames.readysetbet.javafxclient.socketclient.ViewModel.JoinLobbyViewModel;
import com.pastimegames.readysetbet.javafxclient.socketclient.ViewModel.ModelRepresentations.PlayerRepresentation;
import com.pastimegames.readysetbet.javafxclient.socketclient.ViewModel.RaceViewModel;

public class ViewModelFactory
{
  private Model model;
  private RaceViewModel raceViewModel;
  private JoinLobbyViewModel joinLobbyViewModel;
  private BettingBoardViewModel bettingBoardViewModel;
  private PlayerRepresentation player;

  public ViewModelFactory(Model model)
  {
    this.model = model;
    player = new PlayerRepresentation(null, null);
  }

  public JoinLobbyViewModel getJoinLobbyViewModel()
  {
    if(joinLobbyViewModel == null)
    {
      joinLobbyViewModel = new JoinLobbyViewModel(model, player);
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
      bettingBoardViewModel = new BettingBoardViewModel(model, player);
    }
    return bettingBoardViewModel;
  }


}
