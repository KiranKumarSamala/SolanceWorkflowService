package com.solance.workflow.handler;

import com.solance.workflow.entity.InstructionType;
import com.solance.workflow.handler.handlers.DepositHandler;
import com.solance.workflow.handler.handlers.OpenAccountHandler;
import com.solance.workflow.handler.handlers.PaymentHandler;
import com.solance.workflow.handler.handlers.RegisterCustomerHandler;
import com.solance.workflow.handler.handlers.NoOpHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class InstructionHandlerFactoryTest {
    private RegisterCustomerHandler registerCustomerHandler;
    private OpenAccountHandler openAccountHandler;
    private DepositHandler depositHandler;
    private PaymentHandler paymentHandler;
    private NoOpHandler noOpHandler;
    private InstructionHandlerFactory factory;

    @BeforeEach
    void setUp() {
        registerCustomerHandler = mock(RegisterCustomerHandler.class);
        openAccountHandler = mock(OpenAccountHandler.class);
        depositHandler = mock(DepositHandler.class);
        paymentHandler = mock(PaymentHandler.class);
        noOpHandler = mock(NoOpHandler.class);
        factory = new InstructionHandlerFactory(
            registerCustomerHandler,
            openAccountHandler,
            depositHandler,
            paymentHandler,
            noOpHandler
        );
    }

    @Test
    void getHandler_returnsCorrectHandlerForEachType() {
        assertSame(registerCustomerHandler, factory.getHandler(InstructionType.REGISTER));
        assertSame(openAccountHandler, factory.getHandler(InstructionType.OPEN_ACCOUNT));
        assertSame(depositHandler, factory.getHandler(InstructionType.DEPOSIT));
        assertSame(paymentHandler, factory.getHandler(InstructionType.PAYMENT));
    }

    @Test
    void getHandler_returnsNoOpHandlerForNullOrUnknownType() {
        assertSame(noOpHandler, factory.getHandler(null));
        // Simulate unknown type by casting an invalid ordinal (not possible with enum, but test null suffices)
    }
} 