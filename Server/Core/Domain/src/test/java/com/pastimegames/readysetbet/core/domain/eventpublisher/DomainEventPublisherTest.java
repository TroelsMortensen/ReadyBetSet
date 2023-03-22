package com.pastimegames.readysetbet.core.domain.eventpublisher;

import com.pastimegames.readysetbet.core.domain.events.HorseMovedEvent;
import org.junit.jupiter.api.Test;

class DomainEventPublisherTest {

    @Test
    public void testGenericStuff() {
        DomainEventPublisher.instance().subscribe(HorseMovedEvent.type(), (DomainEventListener<HorseMovedEvent>) event -> {
            System.out.println("Here");
        });

        DomainEventPublisher.instance().publish(new HorseMovedEvent("4", 3));

    }

    @Test
    public void testGenericType() {
        DomainEventListener<HorseMovedEvent> lambda = event -> {
            System.out.println("Here");
        };
        method(lambda);
    }

    public <T> void method(DomainEventListener<T> listener) {
//            String typeName = ((ParameterizedType) list.getClass().getGenericInterfaces()[0]).getActualTypeArguments()[0].getTypeName();
        int stopher = 0;
//            System.out.println(typeName);

    }

}