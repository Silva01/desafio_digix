package br.net.digix.desafio.silva.daniel.domain.family.entity;

import static org.junit.jupiter.api.Assertions.*;

import br.net.digix.desafio.silva.daniel.domain.family.value_object.Dependent;
import br.net.digix.desafio.silva.daniel.domain.family.value_object.enuns.DependentGenderEnum;
import br.net.digix.desafio.silva.daniel.domain.family.value_object.enuns.DependentTypeEnum;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Tag("unit")
class FamilyEntityTest {

    @Test
    void deveCriarUmaFamilia() {
        Dependent dependent = new Dependent(
                "Teste",
                LocalDate.of(2021, 11, 11),
                false,
                DependentTypeEnum.FILHO,
                DependentGenderEnum.MASCULINO
        );

        Dependent dependentConjuge = new Dependent(
                "Teste",
                LocalDate.now().minusYears(30),
                false,
                DependentTypeEnum.CONJUGE,
                DependentGenderEnum.FEMININO
        );

        FamilyEntity family = new FamilyEntity(
                "12345678999",
                new BigDecimal(800),
                "Daniel",
                List.of(dependent, dependentConjuge)
        );

        assertEquals(family.getName(), "Daniel");
        assertEquals(family.getIncome(), new BigDecimal(800));
        assertEquals(family.getDependents().size(), 2);
        assertEquals(family.getDependents().get(0).getName(), "Teste");
        assertEquals(family.getDependents().get(0).getBirthDate(), LocalDate.of(2021, 11, 11));
        assertFalse(family.getDependents().get(0).isWork());
        assertEquals(family.getDependents().get(0).getType(), DependentTypeEnum.FILHO);
        assertEquals(family.getDependents().get(0).getGender(), DependentGenderEnum.MASCULINO);
        assertEquals(family.getCpf(), "12345678999");

        assertEquals(family.getPoints(), 0);

    }
}
