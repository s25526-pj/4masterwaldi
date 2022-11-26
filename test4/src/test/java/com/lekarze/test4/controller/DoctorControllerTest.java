package com.lekarze.test4.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lekarze.test4.model.dto.DoctorDto;
import com.lekarze.test4.service.DoctorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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
class DoctorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testFindAll_ShouldReturnAllDoctors() throws Exception {
        DoctorDto doctor1 = DoctorDto.builder()
                .name("Jan")
                .surname("Kowalski")
                .nip(123)
                .build();
        DoctorDto doctor2 = DoctorDto.builder()
                .name("Janek")
                .surname("Kowal")
                .nip(321)
                .build();

        mockMvc.perform(post("/api/v1/doctors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(doctor1)))
                .andExpect(status().isCreated());

        mockMvc.perform(post("/api/v1/doctors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(doctor2)))
                .andExpect(status().isCreated());

        mockMvc.perform(get("/api/v1/doctors"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$.[0].name", equalTo("Jan")))
                .andExpect(jsonPath("$.[0].nip", equalTo(123)))
                .andExpect(jsonPath("$.[1].name", equalTo("Janek")))
                .andExpect(jsonPath("$.[1].nip", equalTo(321)));


    }

    @Test
    void testSave_shouldSaveDoctorToRepository() throws Exception {
        DoctorDto dto = DoctorDto.builder()
                .id(1)
                .name("Jan")
                .surname("Kowalski")
                .nip(123)
                .build();
        mockMvc.perform(post("/api/v1/doctors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.name", equalTo("Jan")))
                .andExpect(jsonPath("$.nip", equalTo(123)));
    }

    @Test
    void delete() {
    }
}