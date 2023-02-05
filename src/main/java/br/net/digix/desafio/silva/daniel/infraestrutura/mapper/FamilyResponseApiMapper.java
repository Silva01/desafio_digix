package br.net.digix.desafio.silva.daniel.infraestrutura.mapper;

import br.net.digix.desafio.silva.daniel.domain.family.entity.FamilyEntity;
import br.net.digix.desafio.silva.daniel.domain.family.value_object.Dependent;
import br.net.digix.desafio.silva.daniel.domain.family.value_object.enuns.DependentGenderEnum;
import br.net.digix.desafio.silva.daniel.domain.family.value_object.enuns.DependentTypeEnum;
import br.net.digix.desafio.silva.daniel.infraestrutura.model.FamilyModel;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FamilyResponseApiMapper implements Function<FamilyModel, FamilyEntity> {
    @Override
    public FamilyEntity apply(FamilyModel familiyModel) {
        List<Dependent> dependents = familiyModel.getDependents().stream()
                .map(dependentModel -> new Dependent(
                        dependentModel.getName(),
                        dependentModel.getBirthDate(),
                        dependentModel.isWorking(),
                        DependentTypeEnum.ofType(dependentModel.getType()),
                        DependentGenderEnum.ofGender(dependentModel.getGender())))
                .collect(Collectors.toList());

        FamilyEntity family = new FamilyEntity(familiyModel.getCpf(), familiyModel.getIncome(), familiyModel.getName(), dependents);
        family.addPoints(familiyModel.getPoints());

        return family;
    }
}
