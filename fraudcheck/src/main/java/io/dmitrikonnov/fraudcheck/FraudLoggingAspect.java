package io.dmitrikonnov.fraudcheck;

import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FraudLoggingAspect {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("within(io.dmitrikonnov.fraudcheck.FraudCheckController)")
    protected void inControllerPoint(){}

    @Pointcut("execution(* *(*))")
    protected void anyExecutionPointWithArgs(){}

    @Pointcut ("execution(* registerCustomer(..))")
    protected void registerCustomerPoint(){}


    @Before("@annotation(io.dmitrikonnov.customer.annotation.Logged) && args(customerRegistrationRequest) && anyExecutionPointWithArgs() && inControllerPoint()")
    public void logRegistrationRequest(CustomerRegistrationRequest customerRegistrationRequest) {
        logger.info(String.format(Messages.FraudCheckMsg,
                customerRegistrationRequest.getFirstName(),
                customerRegistrationRequest.getLastName(),
                customerRegistrationRequest.getEmail(),
                customerRegistrationRequest.getCreatedAt()));

        logger.info("Request: {}", customerRegistrationRequest);
    }

    @Around("@annotation(io.dmitrikonnov.customer.annotation.Logged)")
    public void logCustomerPersistence(){

    }
}
