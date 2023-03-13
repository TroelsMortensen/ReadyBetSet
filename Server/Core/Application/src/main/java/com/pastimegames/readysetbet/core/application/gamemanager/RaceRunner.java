package com.pastimegames.readysetbet.core.application.gamemanager;

import com.pastimegames.readysetbet.core.domain.domainservices.DiceRoller;
import com.pastimegames.readysetbet.core.domain.entities.race.Race;
import com.pastimegames.readysetbet.core.domain.eventpublisher.DomainEventListener;
import com.pastimegames.readysetbet.core.domain.eventpublisher.DomainEventPublisher;
import com.pastimegames.readysetbet.core.domain.events.RaceFinished;
import com.pastimegames.readysetbet.core.domain.events.RaceStarted;
import com.pastimegames.readysetbet.core.domain.entities.lobby.RaceOptions;

public class RaceRunner {

    private boolean raceIsFinished = false;
    private int moveTickTime;
    private Thread thread;

    public RaceRunner(RaceOptions options) {
        moveTickTime = options.moveTickTime();
        DomainEventPublisher.instance().subscribe(RaceFinished.type(), (DomainEventListener<RaceFinished>) raceFinished -> {
            raceIsFinished = true;
            thread.interrupt();
        });
    }

    public void run(Race race, DiceRoller diceRoller) {

        DomainEventPublisher.instance().publish(new RaceStarted());

        thread = new Thread(() -> runRace(race, diceRoller));
        thread.start();
    }

    private void runRace(Race race, DiceRoller diceRoller) {
        try {
            Thread.sleep(2500);

            while (!raceIsFinished) {
                race.moveHorse(diceRoller);
                Thread.sleep(moveTickTime);
            }
        } catch (InterruptedException ignored) {
        }
    }

}
