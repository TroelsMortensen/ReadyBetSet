package com.pastimegames.readysetbet.core.domain.eventpublisher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DomainEventPublisher {

    // Feel like I'm abusing the generic DomainEventListener. Found in Implementing Domain-Driven Design, by Vaughn Vernon.

    private static DomainEventPublisher instance = new DomainEventPublisher();
    private final Map<String, List<DomainEventListener>> listeners = new HashMap<>();

    private DomainEventPublisher(){

    }

    public static DomainEventPublisher instance() {
        return instance;
    }

    public <T> void publish(T event) {
        String eventType = event.getClass().getName();
        if (!listeners.containsKey(eventType)) {
            return;
        }
        for (DomainEventListener<T> listener : listeners.get(eventType)) {
//            new Thread(() -> listener.notify(obj)).start(); todo someday make async?
            listener.notify(event);
        }
    }


    public <T> void subscribe(String eventType, DomainEventListener<T> listener) {

        if (!listeners.containsKey(eventType)) {
            listeners.put(eventType, new ArrayList<>());
        }
        listeners.get(eventType).add(listener);
    }


    public <T> void unsubscribe(String eventType, DomainEventListener<T> listener) {
        if (!listeners.containsKey(eventType)) {
            return;
        }
        listeners.get(eventType).remove(listener);
    }
}
