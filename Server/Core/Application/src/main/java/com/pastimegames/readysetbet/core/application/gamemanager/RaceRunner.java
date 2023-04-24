package com.pastimegames.readysetbet.core.application.gamemanager;

import com.pastimegames.readysetbet.core.domain.domainservices.DiceRoller;
import com.pastimegames.readysetbet.core.domain.entities.race.Race;
import com.pastimegames.readysetbet.core.domain.eventpublisher.DomainEventListener;
import com.pastimegames.readysetbet.core.domain.eventpublisher.DomainEventPublisher;
import com.pastimegames.readysetbet.core.domain.events.RaceFinishedEvent;
import com.pastimegames.readysetbet.core.domain.events.RaceStartedEvent;
import com.pastimegames.readysetbet.core.domain.entities.lobby.RaceOptions;

public class RaceRunner {

    private boolean raceIsFinished = false;
    private int moveTickTime;
    private Thread thread;

    public RaceRunner(RaceOptions options) {
        moveTickTime = options.moveTickTime();
        DomainEventPublisher.instance().subscribe(RaceFinishedEvent.type(), (DomainEventListener<RaceFinishedEvent>) raceFinished -> {
            raceIsFinished = true;
            thread.interrupt();
        });
    }

    public void run(Race race, DiceRoller diceRoller) {

        DomainEventPublisher.instance().publish(new RaceStartedEvent());

        thread = new Thread(() -> runRace(race, diceRoller));
        thread.setDaemon(true);
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
