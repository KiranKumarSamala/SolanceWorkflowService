package com.solance.workflow.handler.handlers;

import com.solance.workflow.dto.InstructionDto;
import com.solance.workflow.entity.InstructionType;
import com.solance.workflow.handler.handlers.NoOpHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class NoOpHandlerTest {
    private NoOpHandler handler;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        handler = new NoOpHandler();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void handle_logsWarning() {
        InstructionDto dto = new InstructionDto(InstructionType.PAYMENT, "{\"to\":\"nobody\"}");
        handler.handle(dto);
        assertTrue(outContent.toString().contains("NoOpHandler: No handler found for instruction type or unhandled type. Payload:"));
    }

    @Test
    void handle_doesNotThrowExceptionForAnyInput() {
        InstructionDto dto = new InstructionDto(null, "{\"foo\":\"bar\"}");
        assertDoesNotThrow(() -> handler.handle(dto));
    }
} 