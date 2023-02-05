package br.net.digix.desafio.silva.daniel.infraestrutura.service;

import br.net.digix.desafio.silva.daniel.domain.family.repository.CriteriaRepository;
import br.net.digix.desafio.silva.daniel.domain.family.value_object.Criteria;
import br.net.digix.desafio.silva.daniel.infraestrutura.mapper.CriteriaMapper;
import br.net.digix.desafio.silva.daniel.infraestrutura.repository.CriteriaModelRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CriteriaService implements CriteriaRepository {

    private final CriteriaModelRepository repository;
    private final CriteriaMapper mapper;

    public CriteriaService(CriteriaModelRepository repository) {
        this.repository = repository;
        this.mapper = new CriteriaMapper();
    }

    @Override
    public List<Criteria> findAllByIsActiveTrue() {
        return repository
                .findAllByActivedIsTrue()
                .stream()
                .map(mapper)
                .collect(Collectors.toList());
    }
}
