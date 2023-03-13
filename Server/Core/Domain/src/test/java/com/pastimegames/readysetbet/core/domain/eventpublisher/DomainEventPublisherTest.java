package com.pastimegames.readysetbet.core.domain.eventpublisher;

import com.pastimegames.readysetbet.core.domain.events.HorseMoved;
import org.junit.jupiter.api.Test;

class DomainEventPublisherTest {

    @Test
    public void testGenericStuff() {
        DomainEventPublisher.instance().subscribe(HorseMoved.type(), (DomainEventListener<HorseMoved>) event -> {
            System.out.println("Here");
        });

        DomainEventPublisher.instance().publish(new HorseMoved("4", 3));

    }

    @Test
    public void testGenericType() {
        DomainEventListener<HorseMoved> lambda = event -> {
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