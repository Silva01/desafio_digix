package br.net.digix.desafio.silva.daniel.infraestrutura.controller;

import br.net.digix.desafio.silva.daniel.domain.family.value_object.FamilyDTO;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.junit.jupiter.api.Assertions.*;

@Tag("integration")
@WebMvcTest(controllers = FamilyController.class)
class FamilyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shlouldCreateFamily() throws Exception {
        this.mockMvc.perform(
                post("/api/v1/family")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON).content("{\"name\":\"Test\",\"income\":800,\"dependents\":[]}"))
                .andExpect(status().isCreated());
    }
}