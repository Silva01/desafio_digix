package br.net.digix.desafio.silva.daniel.infraestrutura.mapper;

import br.net.digix.desafio.silva.daniel.domain.family.value_object.Dependent;
import br.net.digix.desafio.silva.daniel.infraestrutura.model.DependentModel;

import java.util.function.Function;

public class DependentToModelMapper implements Function<Dependent, DependentModel> {
    @Override
    public DependentModel apply(Dependent dependent) {
        DependentModel model = new DependentModel();
        model.setName(dependent.getName());
        model.setBirthDate(dependent.getBirthDate());
        model.setType(dependent.getType().name());
        model.setGender(dependent.getGender().name());
        model.setWorking(dependent.isWork());
        return model;
    }
}
