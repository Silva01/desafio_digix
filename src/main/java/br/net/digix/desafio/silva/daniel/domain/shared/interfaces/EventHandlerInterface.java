package br.net.digix.desafio.silva.daniel.domain.shared.interfaces;

public interface EventHandlerInterface<T extends EventInterface> {
    void handle(T event);
}
