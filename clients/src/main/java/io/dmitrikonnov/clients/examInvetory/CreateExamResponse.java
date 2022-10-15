package io.dmitrikonnov.clients.examInvetory;

import lombok.Data;

import java.util.UUID;

@Data
public class CreateExamResponse {
    private Integer stock;
    private String name;
    private UUID examInventoryId;

}
