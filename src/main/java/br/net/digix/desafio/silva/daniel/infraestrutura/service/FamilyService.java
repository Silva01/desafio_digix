package br.net.digix.desafio.silva.daniel.infraestrutura.service;

import br.net.digix.desafio.silva.daniel.domain.family.entity.FamilyEntity;
import br.net.digix.desafio.silva.daniel.domain.family.repository.FamilyRepository;
import br.net.digix.desafio.silva.daniel.infraestrutura.mapper.FamilyResponseApiMapper;
import br.net.digix.desafio.silva.daniel.infraestrutura.repository.FamiliyModelRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FamilyService implements FamilyRepository {

    private final FamiliyModelRepository repository;

    private final FamilyResponseApiMapper mapper;

    public FamilyService(final FamiliyModelRepository repository) {
        this.repository = repository;
        this.mapper = new FamilyResponseApiMapper();
    }
    @Override
    public void save(FamilyEntity family) {
        System.out.println("Saving family: " + family);
    }

    @Override
    public List<FamilyEntity> findAll() {
        return repository
                .findAll()
                .stream()
                .map(mapper)
                .collect(Collectors.toList());
    }
}
