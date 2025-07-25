package com.solance.workflow.dto;

import com.solance.workflow.entity.InstructionStatus;
import com.solance.workflow.entity.InstructionType;
import java.time.LocalDateTime;

public record InstructionResponseDto(
    Long id,
    InstructionType type,
    InstructionStatus status,
    String payload,
    LocalDateTime createdAt,
    LocalDateTime processedAt
) {} 