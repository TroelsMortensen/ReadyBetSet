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

        List<Winning> winnings = collectWinningBets(raceResult);
        deliverPayoutsToPlayers(winnings, lobby);
    }

    public void deliverPenalties(RaceResult raceResult, Lobby lobby) {
        List<Penalty> penalties = collectPenalties(raceResult);
        deliverWinningsToPLayers(penalties, lobby);
    }

    private void deliverWinningsToPLayers(List<Penalty> penalties, Lobby lobby) {
        for (Penalty penalty : penalties) {
            Player player = lobby.getPlayer(penalty.playerName);
            player.applyPenalty(penalty.penalty());
        }
    }

    private List<Penalty> collectPenalties(RaceResult raceResult) {
        List<Penalty> penalties = new LinkedList<>();
        for (BetCell betCell : bettingBoard.betCells()) {
            List<Coin> coins = betCell.coins();
            if (coins.isEmpty()) {
                continue;
            }
            String horseName = bettingBoard.betCellIdToHorseName(betCell.id());
            String placement = bettingBoard.betCellIdToPlacement(betCell.id());
            if (isWinningBet(horseName, placement, raceResult)) {
                continue;
            }
            for (Coin coin : coins) {
                penalties.add(new Penalty(coin.owningPlayer(), betCell.penalty()));
            }
        }

        return penalties;
    }

    private void deliverPayoutsToPlayers(List<Winning> winnings, Lobby lobby) {
        for (Winning winning : winnings) {
            Player player = lobby.getPlayer(winning.playerName);
            player.addWinning(winning.payout());
        }
    }

    private List<Winning> collectWinningBets(RaceResult raceResult) {
        List<Winning> winnings = new LinkedList<>();
        for (BetCell betCell : bettingBoard.betCells()) {
            List<Coin> coins = betCell.coins();

            if (coins.isEmpty()) {
                continue;
            }

            String horseName = bettingBoard.betCellIdToHorseName(betCell.id());
            String placement = bettingBoard.betCellIdToPlacement(betCell.id());
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
