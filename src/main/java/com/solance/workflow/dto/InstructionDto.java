package com.solance.workflow.dto;

import com.solance.workflow.entity.InstructionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record InstructionDto(
    @NotNull(message = "Instruction type is required") InstructionType type,
    @NotBlank(message = "Payload must not be blank") String payload
) {} 