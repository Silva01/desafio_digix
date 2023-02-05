package br.net.digix.desafio.silva.daniel.domain.family.service;

import br.net.digix.desafio.silva.daniel.domain.family.entity.FamilyEntity;
import br.net.digix.desafio.silva.daniel.domain.family.interfaces.Calculate;
import br.net.digix.desafio.silva.daniel.domain.family.util.CriteriaUtil;
import br.net.digix.desafio.silva.daniel.domain.family.value_object.Criteria;
import br.net.digix.desafio.silva.daniel.domain.family.value_object.enuns.CriteriaTypeEnum;

import java.util.List;
import java.util.stream.Collectors;

public class CalculateIncomeFamilyService implements Calculate<List<Criteria>, FamilyEntity> {

    @Override
    public void exec(List<Criteria> criteria, FamilyEntity familyEntity) {
        List<Criteria> criteriaIncomes = criteria.stream()
                .filter( c -> c.getType().equals(CriteriaTypeEnum.INCOME))
                .collect(Collectors.toList());

        criteriaIncomes.forEach( criteriaIncome -> {
            if (CriteriaUtil.isMajorAndEqualsWithNull(familyEntity.getIncome().intValue(), criteriaIncome.getInitialCriteria()) && CriteriaUtil.isMinorAndEqualsWithNull(familyEntity.getIncome().intValue(), criteriaIncome.getFinalCriteria())) {
                familyEntity.addPoints(criteriaIncome.getPoints());
            }
        });
    }
}
