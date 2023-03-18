package com.pastimegames.readysetbet.core.domain.entities.betting;

import com.pastimegames.readysetbet.core.domain.entities.lobby.Lobby;
import com.pastimegames.readysetbet.core.domain.entities.lobby.Player;
import com.pastimegames.readysetbet.core.domain.entities.race.RaceResult;

import java.util.LinkedList;
import java.util.List;

public class BookMaker {

    private BettingBoard bettingBoard;

    public BookMaker() {
        bettingBoard = new BettingBoard();
    }

    public void betOnCell(int index, Coin coin) {
        bettingBoard.placeBetOnCell(index, coin);
    }

    public void deliverWinnings(RaceResult raceResult, Lobby lobby) {

        List<Winning> winnings = getWinningBets(raceResult);
        deliverPayoutToPlayer(winnings, lobby);
    }

    public void deliverPenalties(RaceResult raceResult, Lobby lobby) {
        List<Penalty> penalties = getPenalties(raceResult);
        // TODO Here fix penalties
    }

    private List<Penalty> getPenalties(RaceResult raceResult) {
        return null;
    }

    private void deliverPayoutToPlayer(List<Winning> winnings, Lobby lobby) {
        for (Winning winning : winnings) {
            Player player = lobby.getPlayer(winning.playerName);
            player.addWinning(winning.payout());
        }
    }

    private List<Winning> getWinningBets(RaceResult raceResult) {
        List<Winning> winnings = new LinkedList<>();
        for (BetCell betCell : bettingBoard.betCells()) {
            List<Coin> coins = betCell.coins();

            if (coins.isEmpty()) {
                continue;
            }

            String horseName = bettingBoard.betCellIdToHorseName(betCell.id());
            String placement = bettingBoard.betCellIdPlacement(betCell.id());
            if (!isWinningBet(horseName, placement, raceResult)) {
                continue;
            }

            for (Coin coin : coins) {
                winnings.add(new Winning(coin.owningPlayer(), coin.value() * betCell.payoutMultiplier()));
            }
        }
        return winnings;
    }

    private boolean isWinningBet(String horseName, String placement, RaceResult raceResult) {
        return switch (placement) {
            case "WIN" -> raceResult.isWIN(horseName);
            case "PLACE" -> raceResult.isPLACE(horseName);
            case "SHOW" -> raceResult.isSHOW(horseName);
            default -> false;
        };
    }

    private record Winning(String playerName, int payout) {
    }

    private record Penalty(String playerName, int penalty) {
    }


}
