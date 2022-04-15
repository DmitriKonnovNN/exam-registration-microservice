package io.dmitrikonnov.customer;

import io.dmitrikonnov.annotation.Logged;
import io.dmitrikonnov.clients.fraud.FraudCheckClient;
import io.dmitrikonnov.clients.fraud.FraudCheckResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class CustomerRegistrationServiceImpl implements CustomerRegistrationService{

    CustomerRepo customerRepo;
    RestTemplate restTemplate;
    FraudCheckClient fraudCheckClient;

    protected FraudCheckResponse checkIfFraud (Long customerId){

        return fraudCheckClient.checkIfCustomerIsFraudster(customerId);
    }

    @Logged
    protected void registerCustomer(CustomerRegistrationRequest registrationRequest) {
        Customer customer = Customer.builder()
                .firstName(registrationRequest.getFirstName())
                .lastName(registrationRequest.getLastName())
                .email(registrationRequest.getEmail()).build();
        customerRepo.saveAndFlush(customer);

        final FraudCheckResponse isFraudster = checkIfFraud(customer.getId());
        if (isFraudster.getIsFraudster()) {
            throw new IllegalStateException("fraudster");
        }
        //TODO: send notifications
    }

}
