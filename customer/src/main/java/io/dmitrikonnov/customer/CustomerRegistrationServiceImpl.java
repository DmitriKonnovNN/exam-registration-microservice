package io.dmitrikonnov.customer;


import io.dmitrikonnov.clients.fraud.FraudCheckClient;
import io.dmitrikonnov.clients.fraud.FraudCheckResponse;
import io.dmitrikonnov.clients.notifcations.NotificationRequest;
import lombok.AllArgsConstructor;
import org.assertj.core.util.Arrays;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@Service
@AllArgsConstructor
public class CustomerRegistrationServiceImpl implements CustomerRegistrationService <CustomerRegistrationRequest>{

    CustomerRepo customerRepo;
    FraudCheckClient fraudCheckClient;
    NotificationViaEmail<CompletableFuture<Void>>notifyViaEmail;
    NotificationViaEmail<CompletableFuture<String>>notifyViaEmailWithResponse;


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

        System.out.println(Thread.currentThread().getName());
        NotificationRequest request = NotificationRequest.builder()
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
                .message("").build();
        notifyViaEmail.notifyViaEmail(request);
            }

}
