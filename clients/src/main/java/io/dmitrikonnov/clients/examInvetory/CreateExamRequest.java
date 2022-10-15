package io.dmitrikonnov.clients.examInvetory;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class CreateExamRequest implements Serializable {
    private String name;
    private Integer initialStock;

}
