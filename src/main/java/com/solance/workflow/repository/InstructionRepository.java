package com.solance.workflow.repository;

import com.solance.workflow.entity.Instruction;
import com.solance.workflow.entity.InstructionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstructionRepository extends JpaRepository<Instruction, Long> {
    List<Instruction> findByType(InstructionType type);
} 