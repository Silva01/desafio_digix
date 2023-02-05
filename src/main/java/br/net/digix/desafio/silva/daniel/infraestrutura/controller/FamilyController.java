package br.net.digix.desafio.silva.daniel.infraestrutura.controller;

import br.net.digix.desafio.silva.daniel.domain.family.factory.FamilyEventFactory;
import br.net.digix.desafio.silva.daniel.domain.family.factory.FamilyFactory;
import br.net.digix.desafio.silva.daniel.domain.family.value_object.FamilyDTO;
import br.net.digix.desafio.silva.daniel.domain.shared.interfaces.EventDispatcherInterface;
import br.net.digix.desafio.silva.daniel.domain.shared.interfaces.EventInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/v1/family")
public class FamilyController {

    @Value("${family.constants.calculate-points}")
    private String calculatePointsIdentify;

    @Value("${family.constants.create-family}")
    private String createFamilyIdentify;

    @Qualifier("familyEventDispatcher")
    private EventDispatcherInterface eventDispatcher;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createFamily(@RequestBody FamilyDTO familyDTO) {
        EventInterface event = FamilyEventFactory.createEventFamily(FamilyFactory.createFamilyEntity(familyDTO));
        eventDispatcher.notify(event, calculatePointsIdentify);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<FamilyDTO> findAll() {
        return Collections.emptyList();
    }
}
