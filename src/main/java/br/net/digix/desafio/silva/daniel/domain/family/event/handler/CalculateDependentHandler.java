package br.net.digix.desafio.silva.daniel.domain.family.event.handler;

import br.net.digix.desafio.silva.daniel.domain.family.entity.FamilyEntity;
import br.net.digix.desafio.silva.daniel.domain.family.repository.CriteriaRepository;
import br.net.digix.desafio.silva.daniel.domain.family.service.CalculateDependentFamilyService;
import br.net.digix.desafio.silva.daniel.domain.family.service.CalculateIncomeFamilyService;
import br.net.digix.desafio.silva.daniel.domain.family.value_object.Criteria;
import br.net.digix.desafio.silva.daniel.domain.shared.interfaces.EventHandlerInterface;
import br.net.digix.desafio.silva.daniel.domain.shared.interfaces.EventInterface;

import java.util.List;

public class CalculateDependentHandler implements EventHandlerInterface<EventInterface> {

    private final CriteriaRepository criteriaRepository;
    private final CalculateIncomeFamilyService calculateIncomeFamilyService;
    private final CalculateDependentFamilyService calculateDependentFamilyService;

    public CalculateDependentHandler(CriteriaRepository criteriaRepository, CalculateIncomeFamilyService calculateIncomeFamilyService, CalculateDependentFamilyService calculateDependentFamilyService) {
        this.criteriaRepository = criteriaRepository;
        this.calculateIncomeFamilyService = calculateIncomeFamilyService;
        this.calculateDependentFamilyService = calculateDependentFamilyService;
    }

    @Override
    public void handle(EventInterface event) {
        List<Criteria> criterias = this.criteriaRepository.findAllByIsActiveTrue();
        FamilyEntity familyEntity = (FamilyEntity) event.payload();

        calculateIncomeFamilyService.exec(criterias, familyEntity);
        calculateDependentFamilyService.exec(criterias, familyEntity);
    }
}
