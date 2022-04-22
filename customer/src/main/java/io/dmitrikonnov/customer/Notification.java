package io.dmitrikonnov.customer;

import java.util.concurrent.CompletableFuture;

public interface Notification <T, V> {
   CompletableFuture<T> notifyViaEmail (V v);

}

