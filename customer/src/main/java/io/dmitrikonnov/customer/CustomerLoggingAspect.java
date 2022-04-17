package io.dmitrikonnov.customer;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class CustomerLoggingAspect {
    Logger logger = LoggerFactory.getLogger(this.getClass());


    @Pointcut("within(io.dmitrikonnov.customer.CustomerController)")
    protected void inControllerPoint(){}

    @Pointcut("execution(* *(*))")
    protected void anyExecutionPointWithArgs(){}

    @Pointcut ("execution(* registerCustomer(..))")
    protected void registerCustomerPoint(){}


    @Before("@annotation(io.dmitrikonnov.annotation.Logged) && args(customerRegistrationRequest) && anyExecutionPointWithArgs() && inControllerPoint()")
    public void logRegistrationRequest(CustomerRegistrationRequest customerRegistrationRequest) {
        logger.info(String.format(Messages.REGISTRATION_REQUEST_ARGS,
                customerRegistrationRequest.getFirstName(),
                customerRegistrationRequest.getLastName(),
                customerRegistrationRequest.getEmail(),
                customerRegistrationRequest.getCreatedAt()));

        logger.info("Request: {}", customerRegistrationRequest);
    }

 /*   @Around("@annotation(annotation.Logged)")
    public void logCustomerPersistence(){

    }*/
}
