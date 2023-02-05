package br.net.digix.desafio.silva.daniel.domain.family.event;

import br.net.digix.desafio.silva.daniel.domain.family.entity.FamilyEntity;
import br.net.digix.desafio.silva.daniel.domain.shared.interfaces.EventInterface;

public class CalculateDependentEvent implements EventInterface {

    private final FamilyEntity familyEntity;

    public CalculateDependentEvent(FamilyEntity familyEntity) {
        this.familyEntity = familyEntity;
    }

    @Override
    public FamilyEntity payload() {
        return this.familyEntity;
    }
}
