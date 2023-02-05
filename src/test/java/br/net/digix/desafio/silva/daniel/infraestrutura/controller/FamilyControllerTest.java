package br.net.digix.desafio.silva.daniel.infraestrutura.controller;

import br.net.digix.desafio.silva.daniel.domain.family.entity.FamilyEntity;
import br.net.digix.desafio.silva.daniel.domain.family.value_object.Dependent;
import br.net.digix.desafio.silva.daniel.domain.family.value_object.enuns.DependentGenderEnum;
import br.net.digix.desafio.silva.daniel.domain.family.value_object.enuns.DependentTypeEnum;
import br.net.digix.desafio.silva.daniel.domain.shared.interfaces.EventDispatcherInterface;
import br.net.digix.desafio.silva.daniel.infraestrutura.service.FamilyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Tag("integration")
@WebMvcTest(controllers = FamilyController.class)
class FamilyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FamilyService service;

    @MockBean(name = "familyEventDispatcher")
    private EventDispatcherInterface eventDispatcher;


    @BeforeEach
    void setUp() {
        Dependent dependentConjuge = new Dependent(
                "Teste",
                LocalDate.of(1993, 2, 5),
                false,
                DependentTypeEnum.CONJUGE,
                DependentGenderEnum.FEMININO
        );

        List<FamilyEntity> families = List.of(new FamilyEntity("12345678900", new BigDecimal("800"), "Test", List.of(dependentConjuge)));
        when(service.findAll()).thenReturn(families);
    }

    @Test
    void shouldCreateFamily() throws Exception {
        this.mockMvc.perform(
                post("/api/v1/family")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON).content("{\"name\":\"Test\",\"cpf\":\"12345678900\",\"income\":800,\"dependents\":[{\"name\": \"dependente 2\", \"birthDate\": \"1993-02-05\", \"type\": \"CONJUGE\", \"gender\": \"FEMININO\", \"work\": false}]}"))
                .andExpect(status().isCreated());
    }

    @Test
    void shouldGetAllFamilies() throws Exception {

        this.mockMvc.perform(
                        post("/api/v1/family")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON).content("{\"name\":\"Test\",\"cpf\":\"12345678900\",\"income\":800,\"dependents\":[{\"name\": \"dependente 2\", \"birthDate\": \"1993-02-05\", \"type\": \"CONJUGE\", \"gender\": \"FEMININO\", \"work\": false}]}"))
                .andExpect(status().isCreated());

        this.mockMvc.perform(
                get("/api/v1/family")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[{\"name\":\"Test\",\"cpf\":\"12345678900\",\"income\":800,\"dependents\":[{\"name\":\"Teste\",\"birthDate\":\"1993-02-05\",\"type\":\"CONJUGE\",\"gender\":\"FEMININO\",\"work\":false}],\"points\":0}]"));
    }

    @Test
    void shouldErrorCreateFamilyDontDependent() throws Exception {
        this.mockMvc.perform(
                        post("/api/v1/family")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON).content("{\"name\":\"Test\",\"cpf\":\"12345678900\",\"income\":800,\"dependents\":[]}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{\"message\":\"Familia sem conjuge\"}"));
    }

    @Test
    void shouldErrorCreateFamilywith2DependentConjuge() throws Exception {
        this.mockMvc.perform(
                        post("/api/v1/family")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON).content("{\"name\":\"Test\",\"cpf\":\"12345678900\",\"income\":800,\"dependents\":[{\"name\":\"Teste\",\"birthDate\":\"1993-02-05\",\"type\":\"CONJUGE\",\"gender\":\"FEMININO\",\"work\":false}, {\"name\":\"Teste2\",\"birthDate\":\"1993-02-05\",\"type\":\"CONJUGE\",\"gender\":\"FEMININO\",\"work\":false}]}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{\"message\":\"Familia com mais de um conjuge\"}"));
    }
}