package io.dmitrikonnov.clients.fraud;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "FRAUDCHECK", url = "${clients.fraudcheck.url}")
public interface FraudCheckClient {
    @GetMapping(path = "api/v1/fraud-check/{customerId}")
    FraudCheckResponse checkIfCustomerIsFraudster (@PathVariable("customerId") Long customerId);


}
