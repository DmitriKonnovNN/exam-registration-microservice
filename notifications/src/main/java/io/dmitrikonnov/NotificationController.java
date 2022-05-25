package io.dmitrikonnov;

import io.dmitrikonnov.clients.notifcations.NotificationRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;

@RestController
@AllArgsConstructor
@RequestMapping ("api/v1.0.0/notify")
public class NotificationController {

    NotificationService<NotificationRequest,String> notificationService;



   @PostMapping
    void sendEmail(@RequestBody NotificationRequest notificationRequestEmail) {

        notificationService.send(notificationRequestEmail);
    }



}
