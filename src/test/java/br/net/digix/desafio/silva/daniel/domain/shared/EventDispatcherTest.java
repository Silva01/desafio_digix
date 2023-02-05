package br.net.digix.desafio.silva.daniel.domain.shared;

import br.net.digix.desafio.silva.daniel.domain.shared.interfaces.EventHandlerInterface;
import br.net.digix.desafio.silva.daniel.domain.shared.interfaces.EventInterface;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Tag("unit")
class EventDispatcherTest {

    @Test
    void shouldRegisterEventHandler() {
        EventDispatcher eventDispatcher = new EventDispatcher();
        EventHandlerInterface eventHandler = new EventHandlerInterface() {
            @Override
            public void handle(EventInterface event) {
                System.out.println("Event handled");
            }
        };
        eventDispatcher.register(eventHandler, "eventName");
        assertTrue(eventDispatcher.getEvents().containsKey("eventName"));
    }

    @Test
    void shouldUnregisterEventHandler() {
        EventDispatcher eventDispatcher = new EventDispatcher();
        EventHandlerInterface eventHandler = new EventHandlerInterface() {
            @Override
            public void handle(EventInterface event) {
                System.out.println("Event handled");
            }
        };
        eventDispatcher.register(eventHandler, "eventName");
        eventDispatcher.unregister(eventHandler, "eventName");
        assertFalse(eventDispatcher.getEvents().containsKey("eventName"));
    }

    @Test
    void shouldUnregisterAllEventHandlers() {
        EventDispatcher eventDispatcher = new EventDispatcher();
        EventHandlerInterface eventHandler = new EventHandlerInterface() {
            @Override
            public void handle(EventInterface event) {
                System.out.println("Event handled");
            }
        };
        eventDispatcher.register(eventHandler, "eventName");
        eventDispatcher.unregisterAll();
        assertFalse(eventDispatcher.getEvents().containsKey("eventName"));
    }

    @Test
    void shouldNotifyEventHandlers() {
        final int[] eventHandled = {0};
        EventDispatcher eventDispatcher = new EventDispatcher();
        EventHandlerInterface eventHandler = new EventHandlerInterface() {
            @Override
            public void handle(EventInterface event) {
                System.out.println("Event handled " + event.payload());
                eventHandled[0]++;
            }
        };

        EventInterface event = new EventInterface() {

            @Override
            public String payload() {
                return "eventName";
            }
        };
        eventDispatcher.register(eventHandler, "eventName");
        eventDispatcher.notify(event, "eventName");

        assertTrue(eventDispatcher.getEvents().containsKey("eventName"));
        assertEquals(1, eventDispatcher.getEvents().get("eventName").size());
        assertEquals(1, eventHandled[0]);
    }

    @Test
    void shouldNotifyEventHandlersWithMultipleEvents() {
        final int[] eventHandled = {0};
        EventDispatcher eventDispatcher = new EventDispatcher();
        EventHandlerInterface eventHandler = new EventHandlerInterface() {
            @Override
            public void handle(EventInterface event) {
                System.out.println("Event handled " + event.payload());
                eventHandled[0]++;
            }
        };

        EventHandlerInterface eventHandler2 = new EventHandlerInterface() {
            @Override
            public void handle(EventInterface event) {
                System.out.println("Event handled 2 " + event.payload());
                eventHandled[0]++;
            }
        };

        EventInterface event = new EventInterface() {

            @Override
            public String payload() {
                return "eventName";
            }
        };
        eventDispatcher.register(eventHandler, "eventName");
        eventDispatcher.register(eventHandler2, "eventName");
        eventDispatcher.notify(event, "eventName");

        assertTrue(eventDispatcher.getEvents().containsKey("eventName"));
        assertEquals(2, eventDispatcher.getEvents().get("eventName").size());
        assertEquals(2, eventHandled[0]);
    }
}