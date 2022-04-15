package io.dmitrikonnov.clients.fraud;

import lombok.Data;

@Data
public class FraudCheckResponse {

    final Long customerId;
    final Boolean isFraudster;
}
