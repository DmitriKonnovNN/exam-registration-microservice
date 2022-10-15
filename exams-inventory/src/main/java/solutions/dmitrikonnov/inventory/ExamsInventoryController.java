package solutions.dmitrikonnov.inventory;

import io.dmitrikonnov.clients.examInvetory.CreateExamRequest;
import io.dmitrikonnov.clients.examInvetory.CreateExamResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class ExamsInventoryController {
    private static Map<UUID, Product> products = new HashMap<>();
    static {
        UUID productId = UUID.fromString("");

        products.put(productId, new Product(productId,"C1 course", 100));
    }


    @PostMapping("/exams")
    CreateExamResponse createExam (@RequestBody CreateExamRequest request){
        CreateExamResponse response = new CreateExamResponse();
        return new CreateExamResponse();
    }
}
