package io.dmitrikonnov;

import io.dmitrikonnov.clients.notifcations.NotificationEmail;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping ("api/v1/notify")
public class NotificationController {

    NotificationService<NotificationEmail,Void> notificationService;

    @PostMapping
    void sendEmail(@RequestBody NotificationEmail notificationEmail){
        notificationService.send(notificationEmail);
    }

}