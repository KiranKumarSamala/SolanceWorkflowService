package com.solance.workflow;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SolanceWorkflowServiceApplication {

    private static final Logger logger = LoggerFactory.getLogger(SolanceWorkflowServiceApplication.class);

    public static void main(String[] args) {
        logger.info("Starting Solance Workflow Service Application...");
        SpringApplication.run(SolanceWorkflowServiceApplication.class, args);
        logger.info("Solance Workflow Service Application started successfully.");
    }
}
