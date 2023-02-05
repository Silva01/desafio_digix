package br.net.digix.desafio.silva.daniel.domain.family.event.handler;

import br.net.digix.desafio.silva.daniel.domain.family.entity.FamilyEntity;
import br.net.digix.desafio.silva.daniel.domain.family.repository.FamilyRepository;
import br.net.digix.desafio.silva.daniel.domain.shared.interfaces.EventHandlerInterface;
import br.net.digix.desafio.silva.daniel.domain.shared.interfaces.EventInterface;

public class SaveFamilyHandler implements EventHandlerInterface<EventInterface> {

    private final FamilyRepository repository;

    public SaveFamilyHandler(FamilyRepository repository) {
        this.repository = repository;
    }

    @Override
    public void handle(EventInterface event) {
        FamilyEntity familyEntity = (FamilyEntity) event.payload();
        repository.save(familyEntity);
    }
}
