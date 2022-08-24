package io.dmitrikonnov.customer;


import io.dmitrikonnov.clients.fraud.FraudCheckClient;
import io.dmitrikonnov.clients.fraud.FraudCheckResponse;
import io.dmitrikonnov.clients.notifcations.NotificationRequest;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.locks.LockSupport;


@AllArgsConstructor
@Slf4j
public class CustomerRegistrationServiceImpl implements CustomerRegistrationService <CustomerRegistrationRequest>{
    private static final String CB_CHECK_IF_FRAUD = "cbCheckIfFraud";
    private static final String RTR_CHECK_IF_FRAUD = "retryCheckIfFraud";
    private static final String CB_FLBCK_MTHD = "checkIfFraudFallBack";
    private static final String RL_CHECK_IF_FRAUD = "rlCheckIfFraud";
    CustomerRepo customerRepo;
    FraudCheckClient fraudCheckClient;
    NotificationViaEmail<String> notifyViaEmail;
    ProceedOrder proceedOrder;


    @CircuitBreaker(name = CB_CHECK_IF_FRAUD, fallbackMethod = CB_FLBCK_MTHD)
    //@Retry(name = RTR_CHECK_IF_FRAUD )
    protected FraudCheckResponse checkIfFraud (Long customerId){

        log.info("check if fraud triggered");
        return fraudCheckClient.checkIfCustomerIsFraudster(customerId);
    }

    public FraudCheckResponse checkIfFraudFallBack (Long customerId, Exception ex) {
        log.error("Circuit Breaker checkIfFraud has kicked off! Caused by: " + ex);
        return new FraudCheckResponse(customerId,true);
    }

    public void registerCustomer(CustomerRegistrationRequest registrationRequest) {
        Customer customer = Customer.builder()
                .firstName(registrationRequest.getFirstName())
                .lastName(registrationRequest.getLastName())
                .email(registrationRequest.getEmail()).build();
        customerRepo.saveAndFlush(customer);

        final FraudCheckResponse isFraudster = checkIfFraud(customer.getId());
        if (isFraudster.getIsFraudster()) {
            throw new IllegalStateException("fraudster");
        }

        NotificationRequest request = NotificationRequest.builder()
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
                .message("first notification").build();


//        Future<ResponseEntity<String>> response = notifyViaEmail.notifyViaEmail(request);
//        System.out.println(LocalDateTime.now());
//        proceedOrder.proceedOrder(response);
//        request.setMessage("second notification");
        notifyViaEmail.notifyViaEmailWithNoResponse(request);
    }

}
