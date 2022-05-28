package io.dmitrikonnov.customer;



import io.dmitrikonnov.clients.notifcations.NotificationClient;
import io.dmitrikonnov.clients.notifcations.NotificationRequest;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;


@Service
@AllArgsConstructor
public class NotificationViaEmailImpl  implements NotificationViaEmail<String> {


    private final NotificationClient notificationClient;



    @Override
    @Async
    public Future<ResponseEntity<String>> notifyViaEmail(NotificationRequest notificationRequest) {

        return  notificationClient.sendEmailWithResponse(notificationRequest);
    }

    @Override
    public void notifyViaEmailWithNoResponse(NotificationRequest notificationRequest) {
        notificationClient.sendEmail(notificationRequest);
    }
}
