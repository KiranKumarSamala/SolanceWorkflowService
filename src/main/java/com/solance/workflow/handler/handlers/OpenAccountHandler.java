package com.solance.workflow.handler.handlers;

import com.solance.workflow.dto.InstructionDto;
import com.solance.workflow.handler.InstructionHandler;
import org.springframework.stereotype.Component;

@Component
public class OpenAccountHandler implements InstructionHandler {
    @Override
    public void handle(InstructionDto request) {
        // Implement open account logic here
        System.out.println("Handling OPEN_ACCOUNT: " + request.payload());
    }
} 