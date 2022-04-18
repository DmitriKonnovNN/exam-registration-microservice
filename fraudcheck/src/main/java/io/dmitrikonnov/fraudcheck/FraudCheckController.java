package io.dmitrikonnov.fraudcheck;

import io.dmitrikonnov.clients.fraud.FraudCheckResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("api/v1/fraud-check")
@AllArgsConstructor
public class FraudCheckController {

    FraudCheckService fraudCheckService;

    @GetMapping(path = "{customerId}")
    public FraudCheckResponse checkIfCustomerIsFraudster (@PathVariable("customerId") Long customerId) {

        return fraudCheckService.checkIfIsFraudster(customerId);
    }
}
