package solutions.dmitrikonnov.inventory;

import lombok.Data;

import java.util.UUID;

@Data
public class Product {
    private final UUID id;
    private final String name;
    private final Integer stock;

}
