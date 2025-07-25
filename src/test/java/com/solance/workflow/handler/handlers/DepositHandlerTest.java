package com.solance.workflow.handler.handlers;

import com.solance.workflow.dto.InstructionDto;
import com.solance.workflow.entity.InstructionType;
import com.solance.workflow.handler.handlers.DepositHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class DepositHandlerTest {
    private DepositHandler handler;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        handler = new DepositHandler();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void handle_printsCorrectPayload() {
        InstructionDto dto = new InstructionDto(InstructionType.DEPOSIT, "{\"amount\":100}");
        handler.handle(dto);
        assertTrue(outContent.toString().contains("Handling DEPOSIT: {\"amount\":100}"));
    }

    @Test
    void handle_doesNotThrowExceptionForValidInput() {
        InstructionDto dto = new InstructionDto(InstructionType.DEPOSIT, "{\"amount\":200}");
        assertDoesNotThrow(() -> handler.handle(dto));
    }
} 