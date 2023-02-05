package br.net.digix.desafio.silva.daniel.infraestrutura.service;

import br.net.digix.desafio.silva.daniel.domain.family.entity.FamilyEntity;
import br.net.digix.desafio.silva.daniel.domain.family.repository.FamilyRepository;
import br.net.digix.desafio.silva.daniel.infraestrutura.exception.CpfJaCadastradoException;
import br.net.digix.desafio.silva.daniel.infraestrutura.mapper.DependentToModelMapper;
import br.net.digix.desafio.silva.daniel.infraestrutura.mapper.FamilyResponseApiMapper;
import br.net.digix.desafio.silva.daniel.infraestrutura.model.FamilyModel;
import br.net.digix.desafio.silva.daniel.infraestrutura.repository.FamiliyModelRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FamilyService implements FamilyRepository {

    private final FamiliyModelRepository repository;

    private final FamilyResponseApiMapper mapper;

    private final DependentToModelMapper dependentMapper;

    public FamilyService(final FamiliyModelRepository repository) {
        this.repository = repository;
        this.mapper = new FamilyResponseApiMapper();
        this.dependentMapper = new DependentToModelMapper();
    }
    @Override
    public void save(FamilyEntity family) {
        FamilyModel model = new FamilyModel();
        model.setName(family.getName());
        model.setIncome(family.getIncome());
        model.setPoints(family.getPoints());
        model.setDependents(family.getDependents().stream().map(dependentMapper).collect(Collectors.toList()));
        model.setCpf(family.getCpf());
        try {
            repository.save(model);
        } catch (DataIntegrityViolationException e) {
            throw new CpfJaCadastradoException();
        }
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
