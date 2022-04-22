package io.dmitrikonnov;

import io.dmitrikonnov.clients.notifcations.NotificationRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping ("api/v1/notify")
public class NotificationController {

    NotificationService<NotificationRequest,Void> notificationService;



    @PostMapping
    void sendEmail(@RequestBody NotificationRequest notificationRequestEmail) throws InterruptedException{
        System.out.println("Notification controller thread: "+Thread.currentThread().getName());
        Thread.sleep(5000);
        notificationService.send(notificationRequestEmail);
    }

}
