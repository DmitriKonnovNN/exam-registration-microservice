package io.dmitrikonnov;

import io.dmitrikonnov.clients.notifcations.NotificationRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1.3.0/notify")
public class NotificationControllerV3 {
    NotificationService<NotificationRequest,String> notificationService;



/*    @PostMapping
    void sendEmail(@RequestBody NotificationRequest notificationRequestEmail) {

        notificationService.send(notificationRequestEmail);
    }*/
}
