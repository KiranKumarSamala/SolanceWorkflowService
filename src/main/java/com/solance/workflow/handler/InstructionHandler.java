package com.solance.workflow.handler;

import com.solance.workflow.dto.InstructionDto;

public interface InstructionHandler {
    void handle(InstructionDto request);
} 