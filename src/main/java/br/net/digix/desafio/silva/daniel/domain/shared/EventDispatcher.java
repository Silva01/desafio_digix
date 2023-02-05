package br.net.digix.desafio.silva.daniel.domain.shared;

import br.net.digix.desafio.silva.daniel.domain.shared.interfaces.EventDispatcherInterface;
import br.net.digix.desafio.silva.daniel.domain.shared.interfaces.EventHandlerInterface;
import br.net.digix.desafio.silva.daniel.domain.shared.interfaces.EventInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class EventDispatcher implements EventDispatcherInterface {

    private final HashMap<String, List<EventHandlerInterface<EventInterface>>> events;

    public EventDispatcher() {
        this.events = new HashMap<>();
    }


    @Override
    public void notify(EventInterface event, String eventName) {
        List<EventHandlerInterface<EventInterface>> eventHandlers = this.events.get(eventName);

        if (eventHandlers == null) {
            return;
        }

        eventHandlers.forEach(eventHandler -> eventHandler.handle(event));
    }

    @Override
    public void register(EventHandlerInterface<EventInterface> event, String eventName) {
        List<EventHandlerInterface<EventInterface>> eventHandlers = this.events.get(eventName);

        if (eventHandlers == null) {
            eventHandlers = new ArrayList<>();
        }

        eventHandlers.add(event);
        this.events.put(eventName, eventHandlers);
    }

    @Override
    public void unregister(EventHandlerInterface<EventInterface> handler, String eventName) {
        List<EventHandlerInterface<EventInterface>> eventHandlers = this.events.get(eventName);

        if (eventHandlers == null) {
            return;
        }

        this.events.remove(eventName);
    }

    @Override
    public void unregisterAll() {
        this.events.clear();
    }

    public HashMap<String, List<EventHandlerInterface<EventInterface>>> getEvents() {
        return events;
    }
}
