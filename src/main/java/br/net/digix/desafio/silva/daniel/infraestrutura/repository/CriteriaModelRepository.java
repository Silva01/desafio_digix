package br.net.digix.desafio.silva.daniel.infraestrutura.repository;

import br.net.digix.desafio.silva.daniel.infraestrutura.model.CriteriaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CriteriaModelRepository extends JpaRepository<CriteriaModel, Long> {
    List<CriteriaModel> findAllByActivedIsTrue();
}
