package com.lekarze.test4.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lekarze.test4.model.dto.PatientDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PatientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testFindAll_ShouldReturnAllPatients() throws Exception {
        PatientDto patient1 = PatientDto.builder()
                .name("Jan")
                .surname("Kowalski")
                .pesel(123)
                .email("michalkalinowski12@gmail.com")
                .build();
        PatientDto patient2 = PatientDto.builder()
                .name("Janek")
                .surname("Kowal")
                .pesel(321)
                .email("michalkalinowski@gmail.com")
                .build();

        mockMvc.perform(post("/api/v1/patients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(patient1)))
                .andExpect(status().isCreated());

        mockMvc.perform(post("/api/v1/patients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(patient2)))
                .andExpect(status().isCreated());

        mockMvc.perform(get("/api/v1/patients"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$.[0].name", equalTo("Jan")))
                .andExpect(jsonPath("$.[1].name", equalTo("Janek")));
    }

    @Test
    void testSave_shouldSavePatientToRepository() throws Exception {
        PatientDto dto = PatientDto.builder()
                .id(1)
                .name("Jan")
                .surname("Kowalski")
                .pesel(1234123)
                .email("michalkalinowski@gmail.com")
                .build();
        mockMvc.perform(post("/api/v1/patients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.name", equalTo(dto.getName())))
                .andExpect(jsonPath("$.email", equalTo(dto.getEmail())));
    }

}