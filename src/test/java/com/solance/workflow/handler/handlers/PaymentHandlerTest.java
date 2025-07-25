package com.solance.workflow.handler.handlers;

import com.solance.workflow.dto.InstructionDto;
import com.solance.workflow.entity.InstructionType;
import com.solance.workflow.handler.handlers.PaymentHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentHandlerTest {
    private PaymentHandler handler;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        handler = new PaymentHandler();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void handle_printsCorrectPayload() {
        InstructionDto dto = new InstructionDto(InstructionType.PAYMENT, "{\"to\":\"bob\",\"amount\":200}");
        handler.handle(dto);
        assertTrue(outContent.toString().contains("Handling PAYMENT: {\"to\":\"bob\",\"amount\":200}"));
    }

    @Test
    void handle_doesNotThrowForValidInput() {
        InstructionDto dto = new InstructionDto(InstructionType.PAYMENT, "{\"to\":\"alice\",\"amount\":300}");
        assertDoesNotThrow(() -> handler.handle(dto));
    }
} 