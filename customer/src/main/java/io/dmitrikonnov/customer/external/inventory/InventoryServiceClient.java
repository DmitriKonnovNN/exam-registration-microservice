package io.dmitrikonnov.customer.external.inventory;


import feign.Headers;
import feign.Param;
import feign.RequestLine;
import io.dmitrikonnov.clients.examInvetory.CreateExamRequest;
import io.dmitrikonnov.clients.examInvetory.CreateExamResponse;
import io.dmitrikonnov.customer.external.config.OffsetDateTimeToMillisExapnder;

import java.time.OffsetDateTime;

public interface InventoryServiceClient {

    @RequestLine("POST /exams")
    @Headers("Content-Type: application/json")
    CreateExamResponse createExam (CreateExamRequest request);

    @RequestLine("POST /exams/{productId}/buy?amount={amount}&boughtAt={boughtAt}")
    void book(@Param ("productId") String productId,
              @Param ("amount")int amount,
              @Param (value = "boughtAt", expander = OffsetDateTimeToMillisExapnder.class)OffsetDateTime boughtAt);
}
