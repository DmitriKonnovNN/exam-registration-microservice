package io.dmitrikonnov.clients.notifcations;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@FeignClient(name = "NOTIFICATION")
public interface NotificationClient {

    @PostMapping(path = "api/v1.2.0/notify")
    Future<ResponseEntity<String>> sendEmailWithResponse(@RequestBody NotificationRequest notificationRequest);

    @PostMapping (path = "api/v1.0.0/notify")
    void sendEmail(@RequestBody NotificationRequest notificationRequestEmail);

}
