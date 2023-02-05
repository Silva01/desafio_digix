package br.net.digix.desafio.silva.daniel.infraestrutura.config;

import br.net.digix.desafio.silva.daniel.domain.family.factory.FamilyEventFactory;
import br.net.digix.desafio.silva.daniel.domain.family.repository.CriteriaRepository;
import br.net.digix.desafio.silva.daniel.domain.family.repository.FamilyRepository;
import br.net.digix.desafio.silva.daniel.domain.shared.EventDispatcher;
import br.net.digix.desafio.silva.daniel.domain.shared.interfaces.EventDispatcherInterface;
import br.net.digix.desafio.silva.daniel.domain.shared.interfaces.EventHandlerInterface;
import br.net.digix.desafio.silva.daniel.domain.shared.interfaces.EventInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EventConfig {

    @Value("${family.constants.calculate-points}")
    private String calculatePointsIdentify;

    @Value("${family.constants.create-family}")
    private String createFamilyIdentify;

    private final CriteriaRepository criteriaRepository;

    private final FamilyRepository familyRepository;

    @Autowired
    public EventConfig(CriteriaRepository criteriaRepository, FamilyRepository familyRepository) {
        this.criteriaRepository = criteriaRepository;
        this.familyRepository = familyRepository;
    }

    @Bean(name = "familyEventDispatcher")
    public EventDispatcherInterface eventDispatcher() {
        EventDispatcher eventDispatcher = new EventDispatcher();
        EventHandlerInterface<EventInterface> calculateHandler = FamilyEventFactory.createCalculatePointsHandler(criteriaRepository);
        EventHandlerInterface<EventInterface> saveFamilyHandler = FamilyEventFactory.createSaveFamilyHandler(familyRepository);

        eventDispatcher.register(calculateHandler, calculatePointsIdentify);
        eventDispatcher.register(saveFamilyHandler, createFamilyIdentify);
        return eventDispatcher;
    }
}
