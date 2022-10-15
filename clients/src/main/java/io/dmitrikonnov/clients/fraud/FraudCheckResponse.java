package io.dmitrikonnov.clients.fraud;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class FraudCheckResponse {

    Long customerId;
    Boolean isFraudster;
}
