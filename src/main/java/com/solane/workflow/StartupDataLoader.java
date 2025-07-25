package com.solance.workflow;

import com.solance.workflow.entity.*;
import com.solance.workflow.repository.InstructionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Component
public class StartupDataLoader implements CommandLineRunner {
    private final InstructionRepository repository;

    public StartupDataLoader(InstructionRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) {
        if (repository.count() == 0) {
            repository.saveAll(Arrays.asList(
                sample(InstructionType.REGISTER, InstructionStatus.RECEIVED, "{\"user\":\"alice\"}"),
                sample(InstructionType.OPEN_ACCOUNT, InstructionStatus.PROCESSED, "{\"account\":\"savings\"}"),
                sample(InstructionType.DEPOSIT, InstructionStatus.FAILED, "{\"amount\":1000}"),
                sample(InstructionType.PAYMENT, InstructionStatus.RECEIVED, "{\"to\":\"bob\",\"amount\":200}"),
                sample(InstructionType.DEPOSIT, InstructionStatus.PROCESSED, "{\"amount\":500}"),
                sample(InstructionType.OPEN_ACCOUNT, InstructionStatus.RECEIVED, "{\"account\":\"checking\"}")
            ));
        }
    }

    private Instruction sample(InstructionType type, InstructionStatus status, String payload) {
        Instruction i = new Instruction();
        i.setType(type);
        i.setStatus(status);
        i.setPayload(payload);
        i.setCreatedAt(LocalDateTime.now().minusDays(1));
        if (status == InstructionStatus.PROCESSED || status == InstructionStatus.FAILED) {
            i.setProcessedAt(LocalDateTime.now());
        }
        return i;
    }
} 