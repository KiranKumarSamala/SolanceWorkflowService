package com.solance.workflow.handler.handlers;

import com.solance.workflow.dto.InstructionDto;
import com.solance.workflow.entity.InstructionType;
import com.solance.workflow.handler.handlers.RegisterCustomerHandler;
import com.solance.workflow.handler.register.RegisterCustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class RegisterCustomerHandlerTest {
    private RegisterCustomerService registerCustomerService;
    private RegisterCustomerHandler handler;

    @BeforeEach
    void setUp() {
        registerCustomerService = mock(RegisterCustomerService.class);
        handler = new RegisterCustomerHandler(registerCustomerService);
    }

    @Test
    void handle_callsRegisterCustomerService() {
        InstructionDto dto = new InstructionDto(InstructionType.REGISTER, "{\"user\":\"alice\"}");
        handler.handle(dto);
        verify(registerCustomerService).registerCustomer(dto);
    }

    @Test
    void handle_doesNotThrowForValidInput() {
        InstructionDto dto = new InstructionDto(InstructionType.REGISTER, "{\"user\":\"bob\"}");
        assertDoesNotThrow(() -> handler.handle(dto));
    }
} 