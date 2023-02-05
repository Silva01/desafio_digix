package br.net.digix.desafio.silva.daniel.domain.family.event;

import br.net.digix.desafio.silva.daniel.domain.family.entity.FamilyEntity;
import br.net.digix.desafio.silva.daniel.domain.shared.interfaces.EventInterface;

public class FamilyEvent implements EventInterface {

    private final FamilyEntity familyEntity;

    public FamilyEvent(FamilyEntity familyEntity) {
        this.familyEntity = familyEntity;
    }

    @Override
    public FamilyEntity payload() {
        return this.familyEntity;
    }
}
