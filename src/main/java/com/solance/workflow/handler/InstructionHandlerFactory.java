package com.solance.workflow.handler;

import com.solance.workflow.entity.InstructionType;
import com.solance.workflow.handler.handlers.DepositHandler;
import com.solance.workflow.handler.handlers.OpenAccountHandler;
import com.solance.workflow.handler.handlers.PaymentHandler;
import com.solance.workflow.handler.handlers.RegisterCustomerHandler;
import com.solance.workflow.handler.handlers.NoOpHandler;
import org.springframework.stereotype.Component;

@Component
public class InstructionHandlerFactory {
    private final RegisterCustomerHandler registerCustomerHandler;
    private final OpenAccountHandler openAccountHandler;
    private final DepositHandler depositHandler;
    private final PaymentHandler paymentHandler;
    private final NoOpHandler noOpHandler;

    public InstructionHandlerFactory(
            RegisterCustomerHandler registerCustomerHandler,
            OpenAccountHandler openAccountHandler,
            DepositHandler depositHandler,
            PaymentHandler paymentHandler,
            NoOpHandler noOpHandler
    ) {
        this.registerCustomerHandler = registerCustomerHandler;
        this.openAccountHandler = openAccountHandler;
        this.depositHandler = depositHandler;
        this.paymentHandler = paymentHandler;
        this.noOpHandler = noOpHandler;
    }

    public InstructionHandler getHandler(InstructionType type) {
        if (type == null) return noOpHandler;
        return switch (type) {
            case REGISTER -> registerCustomerHandler;
            case OPEN_ACCOUNT -> openAccountHandler;
            case DEPOSIT -> depositHandler;
            case PAYMENT -> paymentHandler;
            default -> noOpHandler;
        };
    }
} 