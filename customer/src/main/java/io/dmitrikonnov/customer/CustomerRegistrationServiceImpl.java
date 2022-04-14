package io.dmitrikonnov.customer;

import io.dmitrikonnov.customer.annotation.Logged;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class CustomerRegistrationServiceImpl implements CustomerRegistrationService{

    CustomerRepo customerRepo;
    RestTemplate restTemplate;

    protected Boolean checkIfFraud (Long customerId){
        return restTemplate.getForEntity("http:localhost:8081/api/v1/fraud-check/{customerId}",Boolean.class,customerId)
                .getBody();
    }

    @Logged
    protected void registerCustomer(CustomerRegistrationRequest registrationRequest) {
        Customer customer = Customer.builder()
                .firstName(registrationRequest.getFirstName())
                .lastName(registrationRequest.getLastName())
                .email(registrationRequest.getEmail()).build();
        customerRepo.saveAndFlush(customer);

        final Boolean isFraudster = checkIfFraud(customer.getId());
        if (isFraudster) {
            throw new IllegalStateException("fraudster");
        }
        //TODO: send notifications
    }

}
