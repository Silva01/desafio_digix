package br.net.digix.desafio.silva.daniel.infraestrutura.controller;

import br.net.digix.desafio.silva.daniel.domain.family.entity.FamilyEntity;
import br.net.digix.desafio.silva.daniel.domain.family.factory.FamilyEventFactory;
import br.net.digix.desafio.silva.daniel.domain.family.factory.FamilyFactory;
import br.net.digix.desafio.silva.daniel.domain.family.repository.FamilyRepository;
import br.net.digix.desafio.silva.daniel.domain.family.value_object.FamilyDTO;
import br.net.digix.desafio.silva.daniel.domain.shared.interfaces.EventDispatcherInterface;
import br.net.digix.desafio.silva.daniel.domain.shared.interfaces.EventInterface;
import br.net.digix.desafio.silva.daniel.infraestrutura.service.FamilyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/family")
public class FamilyController {

    @Value("${family.constants.calculate-points}")
    private String calculatePointsIdentify;

    @Value("${family.constants.create-family}")
    private String createFamilyIdentify;

    private final EventDispatcherInterface eventDispatcher;

    private final FamilyRepository familyRepository;

    @Autowired
    public FamilyController(@Qualifier("familyEventDispatcher") EventDispatcherInterface eventDispatcher, FamilyService familyRepository) {
        this.eventDispatcher = eventDispatcher;
        this.familyRepository = familyRepository;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void createFamily(@RequestBody FamilyDTO familyDTO) {
        EventInterface event = FamilyEventFactory.createEventFamily(FamilyFactory.createFamilyEntity(familyDTO));
        eventDispatcher.notify(event, calculatePointsIdentify);
        eventDispatcher.notify(event, createFamilyIdentify);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<FamilyDTO> findAll() {
        return familyRepository
                .findAll()
                .stream()
                .map(FamilyEntity::toDTO)
                .collect(Collectors.toList()).stream()
                .sorted((f1, f2) -> f2.getPoints().compareTo(f1.getPoints()))
                .collect(Collectors.toList());
    }
}
