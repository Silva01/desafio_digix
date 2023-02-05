package br.net.digix.desafio.silva.daniel.infraestrutura.controller;

import br.net.digix.desafio.silva.daniel.domain.family.entity.FamilyEntity;
import br.net.digix.desafio.silva.daniel.domain.shared.interfaces.EventDispatcherInterface;
import br.net.digix.desafio.silva.daniel.infraestrutura.service.FamilyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

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
        List<FamilyEntity> families = List.of(new FamilyEntity("12345678900", new BigDecimal("800"), "Test", Collections.emptyList()));
        when(service.findAll()).thenReturn(families);
    }

    @Test
    void shouldCreateFamily() throws Exception {
        this.mockMvc.perform(
                post("/api/v1/family")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON).content("{\"name\":\"Test\",\"cpf\":\"12345678900\",\"income\":800,\"dependents\":[]}"))
                .andExpect(status().isCreated());
    }

    @Test
    void shouldGetAllFamilies() throws Exception {

        this.mockMvc.perform(
                        post("/api/v1/family")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON).content("{\"name\":\"Test\",\"cpf\":\"12345678900\",\"income\":800,\"dependents\":[]}"))
                .andExpect(status().isCreated());

        this.mockMvc.perform(
                get("/api/v1/family")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[{\"name\":\"Test\",\"cpf\":\"12345678900\",\"income\":800,\"dependents\":[],\"points\":0}]"));
    }
}