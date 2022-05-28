package io.dmitrikonnov.customer;

import io.dmitrikonnov.amqp.RabbitMQMessageProducer;
import io.dmitrikonnov.clients.notifcations.NotificationRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

@Service ("notificationViaRabbitMQ")
@AllArgsConstructor
public class NotificationViaRabbitMQ  implements NotificationViaEmail<String>{

    private final RabbitMQMessageProducer mqMessageProducer;

    //TODO: Rewrite to get responses over rabbitMQ.
    @Override
    public Future<ResponseEntity<String>> notifyViaEmail(NotificationRequest notificationRequest) {

        return null;
    }

    @Override
    public void notifyViaEmailWithNoResponse(NotificationRequest notificationRequest) {
        mqMessageProducer.publish(notificationRequest,"internal.exchange","internal.notification.routing-key");
    }
}
