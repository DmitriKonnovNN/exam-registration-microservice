package io.dmitrikonnov.customer;


import io.dmitrikonnov.clients.notifcations.NotificationClient;
import io.dmitrikonnov.clients.notifcations.NotificationClientResolver;
import io.dmitrikonnov.clients.notifcations.NotificationRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;


@Service
public class NotificationViaEmailImpl <T extends CompletableFuture<T>> implements NotificationViaEmail<T> {


    private final NotificationClient<T> notificationClient;


    public NotificationViaEmailImpl(@Autowired NotificationClientResolver resolver) {
        this.notificationClient = new NotificationClient<T>(resolver);
    }

    @Override
    @Async
    public CompletableFuture<T> notifyViaEmail(NotificationRequest notificationRequest) {

        return  notificationClient.sendEmail(notificationRequest);
    }
}
