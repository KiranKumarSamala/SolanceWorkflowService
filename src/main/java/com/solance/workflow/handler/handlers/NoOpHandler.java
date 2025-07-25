package com.solance.workflow.handler.handlers;

import com.solance.workflow.dto.InstructionDto;
import com.solance.workflow.handler.InstructionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class NoOpHandler implements InstructionHandler {
    private static final Logger logger = LoggerFactory.getLogger(NoOpHandler.class);
    @Override
    public void handle(InstructionDto request) {
        logger.warn("NoOpHandler: No handler found for instruction type or unhandled type. Payload: {}", request.payload());
    }
} 