package br.net.digix.desafio.silva.daniel.domain.family.event;

import br.net.digix.desafio.silva.daniel.domain.family.entity.FamilyEntity;
import br.net.digix.desafio.silva.daniel.domain.family.event.handler.CalculatePointsFamilyHandler;
import br.net.digix.desafio.silva.daniel.domain.family.interfaces.Calculate;
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
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("unit")
class FamilyEventTest {

    private EventDispatcher eventDispatcher = new EventDispatcher();
    private final CalculateDependentFamilyService calculateDependentFamilyService = new CalculateDependentFamilyService();
    private final CalculateIncomeFamilyService calculateIncomeFamilyService = new CalculateIncomeFamilyService();

    private final List<Calculate<List<Criteria>, FamilyEntity>> calculateList = List.of(calculateIncomeFamilyService, calculateDependentFamilyService);

    private final CriteriaRepository criteriaRepository = mock(CriteriaRepository.class);

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

        when(criteriaRepository.findAllByIsActiveTrue()).thenReturn(List.of(criteriaIncomeTest, criteriaDependentTest, criteriaDependentTest2, criteriaIncomeTest2));
    }

    @Test
    void shouldCalculateDependentEvent() {

        Dependent dependent = new Dependent(
                "Teste",
                LocalDate.now().minusYears(4),
                false,
                DependentTypeEnum.FILHO,
                DependentGenderEnum.MASCULINO
        );
        FamilyEntity family = new FamilyEntity(
                "12345678900",
                new BigDecimal(800),
                "Daniel",
                List.of(dependent)
        );

        EventHandlerInterface<EventInterface> eventHandler = new CalculatePointsFamilyHandler(
                criteriaRepository,
                calculateList
        );

        EventInterface event = new FamilyEvent(family);

        eventDispatcher.register(eventHandler, eventHandler.getClass().getName());
        eventDispatcher.notify(event, eventHandler.getClass().getName());

        assertEquals(7, family.getPoints());
    }

    @Test
    void shouldCalculateDontDependentEvent() {

        FamilyEntity family = new FamilyEntity(
                "12345678900",
                new BigDecimal(800),
                "Daniel",
                Collections.emptyList()
        );

        EventHandlerInterface<EventInterface> eventHandler = new CalculatePointsFamilyHandler(
                criteriaRepository,
                calculateList
        );

        EventInterface event = new FamilyEvent(family);

        eventDispatcher.register(eventHandler, eventHandler.getClass().getName());
        eventDispatcher.notify(event, eventHandler.getClass().getName());

        assertEquals(5, family.getPoints());
    }

    @Test
    void shouldCalculateDependentAndNotIncomesValidEvent() {

        Dependent dependent = new Dependent(
                "Teste",
                LocalDate.now().minusYears(4),
                false,
                DependentTypeEnum.FILHO,
                DependentGenderEnum.MASCULINO
        );

        FamilyEntity family = new FamilyEntity(
                "12345678900",
                new BigDecimal(3000),
                "Daniel",
                List.of(dependent)
        );

        EventHandlerInterface<EventInterface> eventHandler = new CalculatePointsFamilyHandler(
                criteriaRepository,
                calculateList
        );

        EventInterface event = new FamilyEvent(family);

        eventDispatcher.register(eventHandler, eventHandler.getClass().getName());
        eventDispatcher.notify(event, eventHandler.getClass().getName());

        assertEquals(2, family.getPoints());
    }

    @Test
    void shouldCalculate3DependentOrMayorEvent() {

        Dependent dependent = new Dependent(
                "Teste",
                LocalDate.now().minusYears(4),
                false,
                DependentTypeEnum.FILHO,
                DependentGenderEnum.MASCULINO
        );

        Dependent dependent2 = new Dependent(
                "Teste",
                LocalDate.now().minusYears(5),
                false,
                DependentTypeEnum.FILHO,
                DependentGenderEnum.MASCULINO
        );

        Dependent dependent3 = new Dependent(
                "Teste",
                LocalDate.now().minusYears(6),
                false,
                DependentTypeEnum.FILHO,
                DependentGenderEnum.MASCULINO
        );

        FamilyEntity family = new FamilyEntity(
                "12345678900",
                new BigDecimal(3000),
                "Daniel",
                List.of(dependent, dependent2, dependent3)
        );

        EventHandlerInterface<EventInterface> eventHandler = new CalculatePointsFamilyHandler(
                criteriaRepository,
                calculateList
        );

        EventInterface event = new FamilyEvent(family);

        eventDispatcher.register(eventHandler, eventHandler.getClass().getName());
        eventDispatcher.notify(event, eventHandler.getClass().getName());

        assertEquals(3, family.getPoints());
    }

    @Test
    void shouldCalculate3DependentOrMayorAndIncomeBetween901And1500Event() {

        Dependent dependent = new Dependent(
                "Teste",
                LocalDate.now().minusYears(4),
                false,
                DependentTypeEnum.FILHO,
                DependentGenderEnum.MASCULINO
        );

        Dependent dependent2 = new Dependent(
                "Teste",
                LocalDate.now().minusYears(5),
                false,
                DependentTypeEnum.FILHO,
                DependentGenderEnum.MASCULINO
        );

        Dependent dependent3 = new Dependent(
                "Teste",
                LocalDate.now().minusYears(6),
                false,
                DependentTypeEnum.FILHO,
                DependentGenderEnum.MASCULINO
        );

        FamilyEntity family = new FamilyEntity(
                "12345678900",
                new BigDecimal(1000),
                "Daniel",
                List.of(dependent, dependent2, dependent3)
        );

        EventHandlerInterface<EventInterface> eventHandler = new CalculatePointsFamilyHandler(
                criteriaRepository,
                calculateList
        );

        EventInterface event = new FamilyEvent(family);

        eventDispatcher.register(eventHandler, eventHandler.getClass().getName());
        eventDispatcher.notify(event, eventHandler.getClass().getName());

        assertEquals(6, family.getPoints());
    }

    @Test
    void shouldCalculateIncomeBetween901And1500Event() {

        FamilyEntity family = new FamilyEntity(
                "12345678900",
                new BigDecimal(1000),
                "Daniel",
                Collections.emptyList()
        );

        EventHandlerInterface<EventInterface> eventHandler = new CalculatePointsFamilyHandler(
                criteriaRepository,
                calculateList
        );

        EventInterface event = new FamilyEvent(family);

        eventDispatcher.register(eventHandler, eventHandler.getClass().getName());
        eventDispatcher.notify(event, eventHandler.getClass().getName());

        assertEquals(3, family.getPoints());
    }

}
