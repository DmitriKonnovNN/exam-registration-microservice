package io.dmitrikonnov.customer;


import io.dmitrikonnov.clients.fraud.FraudCheckClient;
import io.dmitrikonnov.clients.fraud.FraudCheckResponse;
import io.dmitrikonnov.clients.notifcations.NotificationRequest;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
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
public class CustomerRegistrationServiceImpl implements CustomerRegistrationService <CustomerRegistrationRequest>{

    CustomerRepo customerRepo;
    FraudCheckClient fraudCheckClient;
    NotificationViaEmail<String> notifyViaEmail;
    ProceedOrder proceedOrder;


    protected FraudCheckResponse checkIfFraud (Long customerId){

       return fraudCheckClient.checkIfCustomerIsFraudster(customerId);
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
