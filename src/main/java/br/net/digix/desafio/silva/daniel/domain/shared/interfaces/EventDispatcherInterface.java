package br.net.digix.desafio.silva.daniel.domain.shared.interfaces;

public interface EventDispatcherInterface {
    void notify(EventInterface event, String eventName);
    void register(EventHandlerInterface<EventInterface> handler, String eventName);
    void unregister(EventHandlerInterface<EventInterface> handler, String eventName);
    void unregisterAll();
}
