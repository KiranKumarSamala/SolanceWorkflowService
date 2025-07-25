package com.solance.workflow.handler.register;

import com.solance.workflow.dto.InstructionDto;

import org.springframework.stereotype.Service;

@Service
public class DefaultRegisterCustomerService implements RegisterCustomerService {
    @Override
    public void registerCustomer(InstructionDto dto) {
        // Implement registration logic here
        System.out.println("Registering customer: " + dto.payload());
    }
} 