package br.net.digix.desafio.silva.daniel.domain.family.event;

import br.net.digix.desafio.silva.daniel.domain.family.entity.FamilyEntity;
import br.net.digix.desafio.silva.daniel.domain.family.event.handler.CalculateDependentHandler;
import br.net.digix.desafio.silva.daniel.domain.family.repository.CriteriaRepository;
import br.net.digix.desafio.silva.daniel.domain.family.service.CalculateDependentFamilyService;
import br.net.digix.desafio.silva.daniel.domain.family.service.CalculateIncomeFamilyService;
import br.net.digix.desafio.silva.daniel.domain.family.value_object.Criteria;
import br.net.digix.desafio.silva.daniel.domain.family.value_object.Dependent;
import br.net.digix.desafio.silva.daniel.domain.family.value_object.enuns.CriteriaTypeEnum;
import br.net.digix.desafio.silva.daniel.domain.family.value_object.enuns.DependentGenderEnum;
import br.net.digix.desafio.silva.daniel.domain.family.value_object.enuns.DependentTypeEnum;
import br.net.digix.desafio.silva.daniel.domain.shared.EventDispatcher;
import br.net.digix.desafio.silva.daniel.domain.shared.interfaces.EventHandlerInterface;
import br.net.digix.desafio.silva.daniel.domain.shared.interfaces.EventInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("unit")
class CalculateDependentEventTest {

    private EventDispatcher eventDispatcher = new EventDispatcher();
    private CalculateDependentFamilyService calculateDependentFamilyService = new CalculateDependentFamilyService();
    private CalculateIncomeFamilyService calculateIncomeFamilyService = new CalculateIncomeFamilyService();

    private CriteriaRepository criteriaRepository = mock(CriteriaRepository.class);

    @BeforeEach
    void contextLoad() {
        Criteria criteriaIncomeTest = new Criteria(
                CriteriaTypeEnum.INCOME,
                null,
                900,
                true,
                "Criterio para calcular familia com renda de ate 900 reais",
                5,
                null,
                null
        );

        Criteria criteriaIncomeTest2 = new Criteria(
                CriteriaTypeEnum.INCOME,
                901,
                1500,
                true,
                "Criterio para calcular familia com renda de 901 ate 1500 reais",
                3,
                null,
                null
        );

        Criteria criteriaDependentTest = new Criteria(
                CriteriaTypeEnum.DEPENDENT_AGE,
                null,
                17,
                true,
                "Criterio para calcular familia com mais de 3 dependentes",
                3,
                3,
                null
        );

        Criteria criteriaDependentTest2 = new Criteria(
                CriteriaTypeEnum.DEPENDENT_AGE,
                null,
                17,
                true,
                "Criterio para calcular familia com 1 ou 2 dependentes",
                2,
                1,
                2
        );

        when(criteriaRepository.findAll()).thenReturn(List.of(criteriaIncomeTest, criteriaDependentTest, criteriaDependentTest2));
    }

    @Test
    void shouldCalculateDependentEvent() {

        Dependent dependent = new Dependent(
                "Teste",
                LocalDate.of(2021, 11, 11),
                false,
                DependentTypeEnum.FILHO,
                DependentGenderEnum.MASCULINO
        );
        FamilyEntity family = new FamilyEntity(
                new BigDecimal(800),
                "Daniel",
                List.of(dependent)
        );

        EventHandlerInterface<EventInterface> eventHandler = new CalculateDependentHandler(
                criteriaRepository,
                calculateIncomeFamilyService,
                calculateDependentFamilyService
        );

        EventInterface event = new CalculateDependentEvent(family);

        eventDispatcher.register(eventHandler, eventHandler.getClass().getName());
        eventDispatcher.notify(event, eventHandler.getClass().getName());

        assertEquals(7, family.getPoints());
    }

}
