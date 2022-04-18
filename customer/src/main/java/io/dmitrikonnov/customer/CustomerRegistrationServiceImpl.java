package io.dmitrikonnov.customer;


import io.dmitrikonnov.clients.fraud.FraudCheckClient;
import io.dmitrikonnov.clients.fraud.FraudCheckResponse;
import io.dmitrikonnov.clients.notifcations.NotificationClient;
import io.dmitrikonnov.clients.notifcations.NotificationEmail;
import lombok.AllArgsConstructor;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor

public class CustomerRegistrationServiceImpl implements CustomerRegistrationService <CustomerRegistrationRequest>{

    CustomerRepo customerRepo;
    RestTemplate template;
    FraudCheckClient fraudCheckClient;
    NotificationClient notificationClient;



    protected FraudCheckResponse checkIfFraud (Long customerId){

       /* return template.getForEntity("http://FRAUDCHECK:8081/api/v1/fraud-check/{customerId}",FraudCheckResponse.class,customerId).getBody();*/
       return fraudCheckClient.checkIfCustomerIsFraudster(customerId);
    }
    protected void notifyViaEmail (String firstName, String lastName, String email,String message){


        notificationClient.sendEmail(NotificationEmail.builder()
                .email(email)
                .firstName(firstName)
                .lastName(lastName)
                .message(message).build());

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
        //TODO: send notifications
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        notifyViaEmail(customer.getFirstName(), customer.getLastName(), customer.getEmail(), "");

            }

}
