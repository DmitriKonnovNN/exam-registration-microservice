package io.dmitrikonnov.customer.external.inventory;


import feign.Headers;
import feign.RequestLine;
import io.dmitrikonnov.clients.examInvetory.CreateExamRequest;
import io.dmitrikonnov.clients.examInvetory.CreateExamResponse;

public interface InventoryServiceClient {

    @RequestLine("POST /exams")
    @Headers("Content-Type: application/json")
    CreateExamResponse createExam (CreateExamRequest request);
}
