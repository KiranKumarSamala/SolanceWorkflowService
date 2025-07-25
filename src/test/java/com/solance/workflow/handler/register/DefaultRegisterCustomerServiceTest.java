package com.solance.workflow.handler.register;

import com.solance.workflow.dto.InstructionDto;
import com.solance.workflow.entity.InstructionType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class DefaultRegisterCustomerServiceTest {
    private DefaultRegisterCustomerService service;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        service = new DefaultRegisterCustomerService();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void registerCustomer_printsCorrectMessage() {
        InstructionDto dto = new InstructionDto(InstructionType.REGISTER, "{\"user\":\"alice\"}");
        service.registerCustomer(dto);
        assertTrue(outContent.toString().contains("Registering customer: {\"user\":\"alice\"}"));
    }

    @Test
    void registerCustomer_doesNotThrowForValidInput() {
        InstructionDto dto = new InstructionDto(InstructionType.REGISTER, "{\"user\":\"bob\"}");
        assertDoesNotThrow(() -> service.registerCustomer(dto));
    }
} 