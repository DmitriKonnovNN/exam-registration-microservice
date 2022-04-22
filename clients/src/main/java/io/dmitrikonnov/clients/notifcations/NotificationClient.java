package io.dmitrikonnov.clients.notifcations;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "NOTIFICATION")
public interface NotificationClient {

    @PostMapping(path = "api/v1/notify")
    NotificationEmail sendEmail(NotificationEmail notification);
}
