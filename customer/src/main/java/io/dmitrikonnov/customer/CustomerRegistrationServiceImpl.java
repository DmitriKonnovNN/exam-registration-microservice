package io.dmitrikonnov.customer;

public class CustomerRegistrationServiceImpl implements CustomerRegistrationService{

    void registerCustomer(CustomerRegistrationRequest registrationRequest) {
        Customer customer = Customer.builder()
                .firstName(registrationRequest.getFirstName())
                .lastName(registrationRequest.getLastName())
                .password(registrationRequest.getPassword()).build();
    }
}
