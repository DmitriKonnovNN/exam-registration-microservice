package io.dmitrikonnov.clients.fraud;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

@FeignClient(name = "FRAUDCHECK", url = "http://localhost:8081")

public interface FraudCheckClient {
    @GetMapping(path = "api/v1/fraud-check/{customerId}")
    FraudCheckResponse checkIfCustomerIsFraudster (@PathVariable("customerId") Long customerId);


}
