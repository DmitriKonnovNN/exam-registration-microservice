package io.dmitrikonnov;

import io.dmitrikonnov.clients.notifcations.NotificationRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1.2.0/notify")
public class NotificationControllerV2 {
    NotificationService<NotificationRequest,String> notificationService;
    @PostMapping()
    String sendEmailWithResponse(@RequestBody NotificationRequest notificationRequestEmail) {
        return notificationService.sendWithResponse(notificationRequestEmail);

    }
}
