package io.dmitrikonnov.customer;



import io.dmitrikonnov.clients.notifcations.NotificationClient;
import io.dmitrikonnov.clients.notifcations.NotificationRequest;


import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;


@Service ("notificationViaSpringAsync")
@AllArgsConstructor
@Slf4j
public class NotificationViaEmailImpl  implements NotificationViaEmail<String> {

    private static final String CB_NTFCN_VIA_SPNG_ASNC = "cbNotificationViaSpringAsync";

    private final NotificationClient notificationClient;



    @Override
    @Async
    public Future<ResponseEntity<String>> notifyViaEmail(NotificationRequest notificationRequest) {

        return  notificationClient.sendEmailWithResponse(notificationRequest);
    }

    @Override
    @Async
    @CircuitBreaker(name = CB_NTFCN_VIA_SPNG_ASNC, fallbackMethod = "notificationFallBack")
    public void notifyViaEmailWithNoResponse(NotificationRequest notificationRequest) {
        notificationClient.sendEmail(notificationRequest);
    }

    public void notificationFallBack (NotificationRequest notificationRequest,Exception ex) {
        System.out.println("Notification failed. CIRCUIT BREAKER is ON!" + notificationRequest.toString());
        log.error("Circuit Breaker's kicked off! Caused by: " + ex);
    }
}
