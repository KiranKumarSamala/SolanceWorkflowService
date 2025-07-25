package com.solance.workflow.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Instruction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private InstructionType type;

    @Enumerated(EnumType.STRING)
    private InstructionStatus status;

    @Lob
    private String payload;

    private LocalDateTime createdAt;
    private LocalDateTime processedAt;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public InstructionType getType() { return type; }
    public void setType(InstructionType type) { this.type = type; }
    public InstructionStatus getStatus() { return status; }
    public void setStatus(InstructionStatus status) { this.status = status; }
    public String getPayload() { return payload; }
    public void setPayload(String payload) { this.payload = payload; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getProcessedAt() { return processedAt; }
    public void setProcessedAt(LocalDateTime processedAt) { this.processedAt = processedAt; }
} 