package io.dmitrikonnov.fraudcheck;

import io.dmitrikonnov.clients.fraud.FraudCheckResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class FraudCheckService {

    private final FraudCheckHistoryRepo fraudCheckHistoryRepo;

    private  boolean isFraudster (Long id) {
        //TODO: solange ein Mock
        return false;
    }

    public FraudCheckResponse checkIfIsFraudster(Long customerId){
        Boolean isFraudster = isFraudster(customerId);
        fraudCheckHistoryRepo.save(FraudCheckHistory.builder()
                .isFraudster(isFraudster)
                .customerId(customerId)
                .createdAt(LocalDateTime.now())
                .build());
        return new FraudCheckResponse(customerId,isFraudster);
    }

}
