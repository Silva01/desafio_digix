package br.net.digix.desafio.silva.daniel.domain.family.repository;

import br.net.digix.desafio.silva.daniel.domain.family.value_object.Criteria;

import java.util.List;

public interface CriteriaRepository {
    List<Criteria> findAll();
}
