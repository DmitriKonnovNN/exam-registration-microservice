package io.dmitrikonnov.fraudcheck;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FraudCheckHistory {

    @SequenceGenerator(name = "fraud_sequence",
            sequenceName = "fraud_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "fraud_sequence")
    @Id
    private Long id;
    private Long customerId;
    private Boolean isFraudster;
    LocalDateTime createdAt;


}
