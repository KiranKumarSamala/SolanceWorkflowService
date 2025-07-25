package com.solance.workflow.handler.handlers;

import com.solance.workflow.dto.InstructionDto;
import com.solance.workflow.handler.InstructionHandler;
import org.springframework.stereotype.Component;

@Component
public class PaymentHandler implements InstructionHandler {
    @Override
    public void handle(InstructionDto request) {
        // Implement payment logic here
        System.out.println("Handling PAYMENT: " + request.payload());
    }
} 