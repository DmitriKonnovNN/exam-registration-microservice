package io.dmitrikonnov.customer;

import io.dmitrikonnov.clients.notifcations.NotificationClient;
import io.dmitrikonnov.clients.notifcations.NotificationEmail;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NotificationViaEmail implements Notification {

    NotificationClient notificationClient;

    @Override
    @Async
    public void notifyViaEmail(String firstName, String lastName, String email, String message) {
        System.out.println(Thread.currentThread().getName());
        notificationClient.sendEmail(NotificationEmail.builder()
                .email(email)
                .firstName(firstName)
                .lastName(lastName)
                .message(message).build());

    }
}
