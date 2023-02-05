package br.net.digix.desafio.silva.daniel.infraestrutura.repository;

import br.net.digix.desafio.silva.daniel.infraestrutura.model.FamilyModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FamiliyModelRepository extends JpaRepository<FamilyModel, Long> {
}
