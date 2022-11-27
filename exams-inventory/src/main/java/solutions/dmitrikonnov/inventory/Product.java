package solutions.dmitrikonnov.inventory;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
public class Product {
    private final UUID id;
    private String name;
    private Integer stock;
    private OffsetDateTime lastBoughtAt;


}
