package com.solance.workflow.handler.handlers;

import com.solance.workflow.dto.InstructionDto;
import com.solance.workflow.handler.InstructionHandler;
import com.solance.workflow.handler.register.RegisterCustomerService;
import org.springframework.stereotype.Component;

@Component
public class RegisterCustomerHandler implements InstructionHandler {
    private final RegisterCustomerService registerCustomerService;

    public RegisterCustomerHandler(RegisterCustomerService registerCustomerService) {
        this.registerCustomerService = registerCustomerService;
    }

    @Override
    public void handle(InstructionDto request) {
        registerCustomerService.registerCustomer(request);
    }
} 