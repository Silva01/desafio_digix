package br.net.digix.desafio.silva.daniel.domain.family.factory;

import br.net.digix.desafio.silva.daniel.domain.family.entity.FamilyEntity;
import br.net.digix.desafio.silva.daniel.domain.family.value_object.FamilyDTO;

public class FamilyFactory {

    public static FamilyEntity createFamilyEntity(final FamilyDTO familyDTO) {
        return new FamilyEntity(familyDTO.getCpf(), familyDTO.getIncome(), familyDTO.getName(), familyDTO.getDependents());
    }
}
