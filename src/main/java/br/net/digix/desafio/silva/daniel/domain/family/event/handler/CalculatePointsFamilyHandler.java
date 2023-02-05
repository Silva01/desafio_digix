package br.net.digix.desafio.silva.daniel.domain.family.event.handler;

import br.net.digix.desafio.silva.daniel.domain.family.entity.FamilyEntity;
import br.net.digix.desafio.silva.daniel.domain.family.interfaces.Calculate;
import br.net.digix.desafio.silva.daniel.domain.family.repository.CriteriaRepository;
import br.net.digix.desafio.silva.daniel.domain.family.value_object.Criteria;
import br.net.digix.desafio.silva.daniel.domain.shared.interfaces.EventHandlerInterface;
import br.net.digix.desafio.silva.daniel.domain.shared.interfaces.EventInterface;

import java.util.List;

public class CalculatePointsFamilyHandler implements EventHandlerInterface<EventInterface> {

    private final CriteriaRepository criteriaRepository;
    private final List<Calculate<List<Criteria>, FamilyEntity>> calculateList;

    public CalculatePointsFamilyHandler(
            final CriteriaRepository criteriaRepository,
            final List<Calculate<List<Criteria>, FamilyEntity>> calculateList) {
        this.criteriaRepository = criteriaRepository;
        this.calculateList = calculateList;
    }

    @Override
    public void handle(EventInterface event) {
        List<Criteria> criterias = this.criteriaRepository.findAllByIsActiveTrue();
        FamilyEntity familyEntity = (FamilyEntity) event.payload();
        calculateList.forEach(calculate -> calculate.exec(criterias, familyEntity));
    }
}
