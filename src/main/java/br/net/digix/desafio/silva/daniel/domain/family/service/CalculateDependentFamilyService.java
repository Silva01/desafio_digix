package br.net.digix.desafio.silva.daniel.domain.family.service;

import br.net.digix.desafio.silva.daniel.domain.family.entity.FamilyEntity;
import br.net.digix.desafio.silva.daniel.domain.family.interfaces.Calculate;
import br.net.digix.desafio.silva.daniel.domain.family.util.CriteriaUtil;
import br.net.digix.desafio.silva.daniel.domain.family.value_object.Criteria;
import br.net.digix.desafio.silva.daniel.domain.family.value_object.Dependent;
import br.net.digix.desafio.silva.daniel.domain.family.value_object.enuns.CriteriaTypeEnum;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

public class CalculateDependentFamilyService implements Calculate<List<Criteria>, FamilyEntity> {

    @Override
    public void exec(List<Criteria> criteria, FamilyEntity familyEntity) {
        List<Criteria> criteriasDependent = criteria.stream()
                .filter( c -> c.getType().equals(CriteriaTypeEnum.DEPENDENT_AGE))
                .collect(Collectors.toList());


        criteriasDependent.forEach( criteriaDependent -> {
            List<Dependent> dependentsValid = familyEntity.getDependents().stream()
                    .filter( dependent -> CriteriaUtil.isMajorAndEqualsWithNull(calculateAgeToDependent(dependent.getBirthDate()), criteriaDependent.getInitialCriteria()))
                    .filter( dependent -> CriteriaUtil.isMinorAndEqualsWithNull(calculateAgeToDependent(dependent.getBirthDate()), criteriaDependent.getFinalCriteria()))
                    .collect(Collectors.toList());

            if ( CriteriaUtil.isMajorAndEqualsWithNull(dependentsValid.size(), criteriaDependent.getRepeatQuantityMinimumValue()) && CriteriaUtil.isMinorAndEqualsWithNull(dependentsValid.size(), criteriaDependent.getRepeatQuantityMaximumValue())) {
                familyEntity.addPoints(criteriaDependent.getPoints());
            }
        });
    }

    private int calculateAgeToDependent(final LocalDate birthDate) {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }
}
