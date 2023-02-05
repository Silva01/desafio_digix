package br.net.digix.desafio.silva.daniel.infraestrutura.mapper;

import br.net.digix.desafio.silva.daniel.domain.family.value_object.Criteria;
import br.net.digix.desafio.silva.daniel.domain.family.value_object.enuns.CriteriaTypeEnum;
import br.net.digix.desafio.silva.daniel.infraestrutura.model.CriteriaModel;

import java.util.function.Function;

public class CriteriaMapper implements Function<CriteriaModel, Criteria> {
    @Override
    public Criteria apply(CriteriaModel criteriaModel) {
        return new Criteria(
                CriteriaTypeEnum.ofCriteria(criteriaModel.getType()),
                criteriaModel.getInitialCriteria(),
                criteriaModel.getFinalCriteria(),
                criteriaModel.isActived(),
                criteriaModel.getDescription(),
                criteriaModel.getPoints(),
                criteriaModel.getMinimumRepeatCriteria(),
                criteriaModel.getMaximumRepeatCriteria()
        );
    }
}
