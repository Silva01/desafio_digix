package br.net.digix.desafio.silva.daniel.domain.family.factory;

import br.net.digix.desafio.silva.daniel.domain.family.entity.FamilyEntity;
import br.net.digix.desafio.silva.daniel.domain.family.event.FamilyEvent;
import br.net.digix.desafio.silva.daniel.domain.family.event.handler.CalculatePointsFamilyHandler;
import br.net.digix.desafio.silva.daniel.domain.family.event.handler.SaveFamilyHandler;
import br.net.digix.desafio.silva.daniel.domain.family.repository.CriteriaRepository;
import br.net.digix.desafio.silva.daniel.domain.family.repository.FamilyRepository;
import br.net.digix.desafio.silva.daniel.domain.family.service.CalculateDependentFamilyService;
import br.net.digix.desafio.silva.daniel.domain.family.service.CalculateIncomeFamilyService;
import br.net.digix.desafio.silva.daniel.domain.shared.interfaces.EventHandlerInterface;
import br.net.digix.desafio.silva.daniel.domain.shared.interfaces.EventInterface;

import java.util.List;

public class FamilyEventFactory {

    public static EventHandlerInterface<EventInterface> createCalculatePointsHandler(final CriteriaRepository criteriaRepository) {
        return new CalculatePointsFamilyHandler(
                criteriaRepository,
                List.of(
                        new CalculateDependentFamilyService(),
                        new CalculateIncomeFamilyService()
                ));
    }

    public static EventHandlerInterface<EventInterface> createSaveFamilyHandler(final FamilyRepository familyRepository) {
        return new SaveFamilyHandler(familyRepository);
    }

    public static EventInterface createEventFamily(final FamilyEntity familyEntity) {
        return new FamilyEvent(familyEntity);
    }
}
