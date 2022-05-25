package io.dmitrikonnov.customer;

import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.locks.LockSupport;

@Service
public class ProceedOrder {

    @Async
    @SneakyThrows
    public void proceedOrder (Future<ResponseEntity<String>> response)  {
        Thread.sleep(2000);
        System.out.println("response" + response);
        System.out.println("response.get"+ response.get());
        System.out.println("Body" + response.get().getBody());
    }
}
