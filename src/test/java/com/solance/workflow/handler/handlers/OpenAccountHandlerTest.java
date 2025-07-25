package com.solance.workflow.handler.handlers;

import com.solance.workflow.dto.InstructionDto;
import com.solance.workflow.entity.InstructionType;
import com.solance.workflow.handler.handlers.OpenAccountHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class OpenAccountHandlerTest {
    private OpenAccountHandler handler;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        handler = new OpenAccountHandler();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void handle_printsCorrectPayload() {
        InstructionDto dto = new InstructionDto(InstructionType.OPEN_ACCOUNT, "{\"account\":\"savings\"}");
        handler.handle(dto);
        assertTrue(outContent.toString().contains("Handling OPEN_ACCOUNT: {\"account\":\"savings\"}"));
    }

    @Test
    void handle_doesNotThrowExceptionForValidInput() {
        InstructionDto dto = new InstructionDto(InstructionType.OPEN_ACCOUNT, "{\"account\":\"checking\"}");
        assertDoesNotThrow(() -> handler.handle(dto));
    }
} 