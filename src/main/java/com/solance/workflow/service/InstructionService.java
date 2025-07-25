package com.solance.workflow.service;

import com.solance.workflow.dto.InstructionDto;
import com.solance.workflow.entity.*;
import com.solance.workflow.repository.InstructionRepository;
import org.springframework.stereotype.Service;
import com.solance.workflow.dto.InstructionResponseDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InstructionService {
    private final InstructionRepository repository;

    public InstructionService(InstructionRepository repository) {
        this.repository = repository;
    }

    public InstructionResponseDto submitInstruction(InstructionDto dto) {
        Instruction instruction = new Instruction();
        instruction.setType(dto.type());
        instruction.setPayload(dto.payload());
        instruction.setStatus(InstructionStatus.RECEIVED);
        instruction.setCreatedAt(LocalDateTime.now());
        Instruction saved = repository.save(instruction);
        return toResponseDto(saved);
    }

    public List<InstructionResponseDto> getAllInstructions(Optional<InstructionType> type) {
        List<Instruction> instructions = type.map(repository::findByType).orElseGet(repository::findAll);
        return instructions.stream().map(this::toResponseDto).collect(Collectors.toList());
    }

    public InstructionResponseDto processInstruction(Long id) {
        Instruction instruction = repository.findById(id)
                .orElseThrow(() -> new java.util.NoSuchElementException("Instruction not found: " + id));
        instruction.setStatus(InstructionStatus.PROCESSED);
        instruction.setProcessedAt(LocalDateTime.now());
        Instruction saved = repository.save(instruction);
        return toResponseDto(saved);
    }

    private InstructionResponseDto toResponseDto(Instruction instruction) {
        return new InstructionResponseDto(
            instruction.getId(),
            instruction.getType(),
            instruction.getStatus(),
            instruction.getPayload(),
            instruction.getCreatedAt(),
            instruction.getProcessedAt()
        );
    }
} 