package com.pastimegames.readysetbet.javafxclient.socketclient.ViewModel;

import com.pastimegames.readysetbet.javafxclient.socketclient.Model.Bet;
import com.pastimegames.readysetbet.javafxclient.socketclient.Model.Model;

public class BettingBoardViewModel {
    private Model model;

    public BettingBoardViewModel(Model model)
    {
        this.model = model;
    }


    public void placeBet(Bet bet) {
        model.placeBet(bet);
    }
}
