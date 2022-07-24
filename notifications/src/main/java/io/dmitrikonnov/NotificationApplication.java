package io.dmitrikonnov;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication //(scanBasePackages = {"io.dmitrikonnov.amqp", "io.dmitrikonnov.notifications"})
@EnableEurekaClient
@PropertySources({@PropertySource("classpath:clients-${spring.profiles.active}.properties")})
public class NotificationApplication {
    public static void main(String[] args) {
        SpringApplication.run(NotificationApplication.class, args);
    }

/*    @Bean
    CommandLineRunner commandLineRunner(RabbitMQMessageProducer producer, NotificationConfig config) {
        return args -> {
            producer.publish("foo", config.getInternalExchange(),config.getInternalNotificationRoutingKey());
        };
    }*/
}
