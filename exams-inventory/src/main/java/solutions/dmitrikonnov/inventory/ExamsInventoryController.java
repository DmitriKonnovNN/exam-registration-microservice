package solutions.dmitrikonnov.inventory;

import io.dmitrikonnov.clients.examInvetory.CreateExamRequest;
import io.dmitrikonnov.clients.examInvetory.CreateExamResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class ExamsInventoryController {
    private static Map<UUID, Product> products = new HashMap<>();
    static {
        UUID productId = UUID.fromString("");

        products.put(productId, new Product(productId,"C1 course", 100, null));
    }


    @PostMapping("/exams")
    CreateExamResponse createExam (@RequestBody CreateExamRequest request){
        CreateExamResponse response = new CreateExamResponse();
        return new CreateExamResponse();
    }

    @PostMapping("/products/{productId}/buy")
    ResponseEntity<?> book(@PathVariable("productId") UUID productId,
                           @RequestParam (value = "amount", required = false, defaultValue = "1") int amount,
                           @RequestParam (value = "boughtAt")OffsetDateTime boughtAt) {

        Product product = products.get(productId);
        int currentStock = product.getStock();
        product.setStock(currentStock - amount);
        product.setLastBoughtAt(boughtAt);

        return ResponseEntity.ok().build();
    }
}
