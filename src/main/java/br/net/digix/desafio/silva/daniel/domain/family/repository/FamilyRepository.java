package br.net.digix.desafio.silva.daniel.domain.family.repository;

import br.net.digix.desafio.silva.daniel.domain.family.entity.FamilyEntity;

import java.util.List;
import java.util.Optional;

public interface FamilyRepository {
        void save(FamilyEntity family);
        List<FamilyEntity> findAll();
}
