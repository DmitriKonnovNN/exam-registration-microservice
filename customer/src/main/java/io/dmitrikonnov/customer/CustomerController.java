package io.dmitrikonnov.customer;

import io.dmitrikonnov.annotation.Logged;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RequestMapping("api/v1/customer")
@RestController
@AllArgsConstructor
public class CustomerController{

    CustomerRegistrationServiceImpl customerService;
    @PostMapping
    @Logged
    public void registerCustomer (@Valid @RequestBody CustomerRegistrationRequest customerRegistrationRequest)
    {
        customerService.registerCustomer(customerRegistrationRequest);
    }

}
