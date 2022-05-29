package io.dmitrikonnov.customer;

import io.dmitrikonnov.clients.fraud.FraudCheckClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor (onConstructor_ = {@Autowired} )
@Slf4j
public class CustomerIPCConfig {


    private final String C_IPC_CONF_MSG = "Current Customer InterProcessorCommunication Configuration: {}";
    private final ApplicationContext context;
    private final FraudCheckClient fraudCheckClient;
    private final ProceedOrder proceedOrder;
    private final  CustomerRepo customerRepo;
    @Value ("${customer-service.ipc-configuration}") private final String qualifier;


    @Bean
    public CustomerRegistrationService<CustomerRegistrationRequest> customerRegistrationServiceImpl() {
        NotificationViaEmail<String> notificationViaEmail = (NotificationViaEmail<String>) context.getBean(qualifier);
        log.info(C_IPC_CONF_MSG, qualifier);
        return new CustomerRegistrationServiceImpl(customerRepo,fraudCheckClient,notificationViaEmail,proceedOrder);
    }

}
