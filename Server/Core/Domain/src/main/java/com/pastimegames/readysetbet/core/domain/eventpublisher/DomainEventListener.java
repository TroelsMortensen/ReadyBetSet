package com.pastimegames.readysetbet.core.domain.eventpublisher;

public interface DomainEventListener <T> {
    void notify(T event);
}
