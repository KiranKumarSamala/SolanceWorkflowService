package com.solance.workflow.controller;

import com.solance.workflow.dto.InstructionDto;
import com.solance.workflow.entity.InstructionType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class InstructionControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void submitInstruction_validRequest_returnsOk() throws Exception {
        String json = "{\"type\":\"DEPOSIT\",\"payload\":\"{\\\"amount\\\":123}\"}";
        mockMvc.perform(post("/api/instructions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());
    }
} 