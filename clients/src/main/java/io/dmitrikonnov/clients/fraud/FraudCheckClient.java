package io.dmitrikonnov.clients.fraud;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "fraudcheck" )
public interface FraudCheckClient {
    @GetMapping(path = "api/v1/fraud-check{customerId}")
    public FraudCheckResponse checkIfCustomerIsFraudster (@PathVariable("customerId") Long customerId);
}
