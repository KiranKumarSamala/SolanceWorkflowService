package com.solance.workflow.controller;

import com.solance.workflow.dto.InstructionDto;
import com.solance.workflow.dto.InstructionResponseDto;
import com.solance.workflow.entity.InstructionType;
import com.solance.workflow.handler.InstructionHandlerFactory;
import com.solance.workflow.service.InstructionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/api/instructions")
@CrossOrigin
@Tag(name = "Instructions", description = "Instruction workflow API")
public class InstructionController {
    private final InstructionService service;
    private final InstructionHandlerFactory handlerFactory;

    public InstructionController(InstructionService service, InstructionHandlerFactory handlerFactory) {
        this.service = service;
        this.handlerFactory = handlerFactory;
    }

    /* TODO: In future, decouple write path using message queue (e.g., Kafka/RabbitMQ).
       Instead of saving directly, publish InstructionMessage to a queue.
       This helps handle high-throughput writes (e.g., 100 TPS), supports retries, and improves resilience.
    */
    @Operation(summary = "Submit a new instruction", description = "Submits a new instruction.")
    @PostMapping
    public InstructionResponseDto submitInstruction(@Valid @RequestBody InstructionDto dto) {
        // Delegate to the correct handler before saving
        handlerFactory.getHandler(dto.type()).handle(dto);
        return service.submitInstruction(dto);
    }

    @Operation(summary = "Get all instructions", description = "Returns all instructions, optionally filtered by type.")
    @GetMapping
    public List<InstructionResponseDto> getAllInstructions(@RequestParam(value = "type", required = false) InstructionType type) {
        return service.getAllInstructions(Optional.ofNullable(type));
    }

    @Operation(summary = "Process an instruction", description = "Manually processes the instruction with the given ID.")
    @PutMapping("/{id}/process")
    public ResponseEntity<InstructionResponseDto> processInstruction(@PathVariable Long id) {
        try {
            InstructionResponseDto updated = service.processInstruction(id);
            return ResponseEntity.ok(updated);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
} 