package io.dmitrikonnov.customer;

import io.dmitrikonnov.customer.annotation.Logged;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("api/v1/customer")
@RestController
@AllArgsConstructor
public class CustomerController{

    CustomerRegistrationServiceImpl customerService;
    @PostMapping
    @Logged
    public void registerCustomer (@RequestBody CustomerRegistrationRequest customerRegistrationRequest)
    {
        customerService.registerCustomer(customerRegistrationRequest);
    }

}
