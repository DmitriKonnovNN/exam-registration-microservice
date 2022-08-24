package io.dmitrikonnov.fraudcheck;

import io.dmitrikonnov.clients.fraud.FraudCheckResponse;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("api/v1/fraud-check")
@Slf4j
@AllArgsConstructor
public class FraudCheckController {

    FraudCheckService fraudCheckService;

    @GetMapping(path = "{customerId}")
    @RateLimiter(name = "rl")
    public FraudCheckResponse checkIfCustomerIsFraudster (@PathVariable("customerId") Long customerId) {
        log.error(customerId.toString());
        return fraudCheckService.checkIfIsFraudster(customerId);
    }
}
