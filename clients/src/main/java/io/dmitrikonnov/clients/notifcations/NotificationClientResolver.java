package io.dmitrikonnov.clients.notifcations;

import com.fasterxml.jackson.databind.JsonNode;
import com.jayway.jsonpath.internal.filter.ValueNodes;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "NOTIFICATION")
public interface NotificationClientResolver {

    @PostMapping(path = "api/v1/notify")
    JsonNode sendEmail(NotificationRequest notificationRequest);
}
