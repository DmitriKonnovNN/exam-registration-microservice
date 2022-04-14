package io.dmitrikonnov.fraudcheck;

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

    public Boolean checkIfIsFraudster(Long customerId){
        boolean isFraudster = isFraudster(customerId);
        fraudCheckHistoryRepo.save(FraudCheckHistory.builder()
                .isFraudster(isFraudster)
                .customerId(customerId)
                .createdAt(LocalDateTime.now())
                .build());
        return isFraudster;
    }

}
