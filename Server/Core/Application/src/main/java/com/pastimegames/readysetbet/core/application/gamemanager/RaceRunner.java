package com.pastimegames.readysetbet.core.application.gamemanager;

import com.pastimegames.readysetbet.core.domain.domainservices.DiceRoller;
import com.pastimegames.readysetbet.core.domain.entities.race.Race;
import com.pastimegames.readysetbet.core.domain.eventpublisher.DomainEventListener;
import com.pastimegames.readysetbet.core.domain.eventpublisher.DomainEventPublisher;
import com.pastimegames.readysetbet.core.domain.events.RaceFinished;
import com.pastimegames.readysetbet.core.domain.events.RaceStarted;

public class RaceRunner {

    private boolean raceIsFinished = false;

    public RaceRunner() {
        DomainEventPublisher.instance().subscribe(RaceFinished.type(), (DomainEventListener<RaceFinished>) raceFinished -> {
            raceIsFinished = true;
        });
    }

    public void run(Race race, DiceRoller diceRoller) {

        DomainEventPublisher.instance().publish(new RaceStarted());

        try {
            Thread.sleep(2500);

            while (!raceIsFinished) {
                race.moveHorse(diceRoller);
                Thread.sleep(2500);
            }
        } catch (InterruptedException ignored) {
        }
    }

}
