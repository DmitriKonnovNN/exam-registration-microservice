package io.dmitrikonnov.fraudcheck;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Aspect
public class FraudLoggingAspect {


    @Pointcut("within(io.dmitrikonnov.fraudcheck.FraudCheckController)")
    protected void inControllerPoint(){}

    @Pointcut("execution(* *(*))")
    protected void anyExecutionPointWithArgs(){}

    @Pointcut ("execution(* registerCustomer(..))")
    protected void registerCustomerPoint(){}





}
