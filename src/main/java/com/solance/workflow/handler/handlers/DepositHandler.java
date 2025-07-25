package com.solance.workflow.handler.handlers;

import com.solance.workflow.dto.InstructionDto;
import com.solance.workflow.handler.InstructionHandler;
import org.springframework.stereotype.Component;

@Component
public class DepositHandler implements InstructionHandler {
    @Override
    public void handle(InstructionDto request) {
        // Implement deposit logic here
        System.out.println("Handling DEPOSIT: " + request.payload());
    }
} 