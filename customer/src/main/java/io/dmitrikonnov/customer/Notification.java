package io.dmitrikonnov.customer;

import org.springframework.http.ResponseEntity;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public interface Notification <T, V> {
   Future<ResponseEntity<T>> notifyViaEmail (V v);
   void notifyViaEmailWithNoResponse(V v);

}

