package io.dmitrikonnov;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
@EnableAspectJAutoProxy
@Getter
@Slf4j
public class NotificationConfig {

    private final int avlblPrccrs = Runtime.getRuntime().availableProcessors();

    @Bean (name = "taskExecutor")
    public TaskExecutor taskExecutor (){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(avlblPrccrs);
        executor.setMaxPoolSize(avlblPrccrs*3);
        executor.setKeepAliveSeconds(45);
        executor.setQueueCapacity(256);
        executor.setThreadNamePrefix("NotificationAsync-Executor-");
        executor.initialize();
        log.debug("Notification AsyncExecutor's bean name = taskExecutor, poolSize = " + avlblPrccrs);
        return executor;
    }


    @Value("${rabbitmq.exchanges.internal}")
    private String internalExchange;

    @Value("${rabbitmq.queue.notification}")
    private String notificationQueue;

    @Value("${rabbitmq.routing-keys.internal-notification}")
    private String internalNotificationRoutingKey;

    @Bean
    public TopicExchange internalTopicExchange(){
        return new TopicExchange(this.internalExchange);
    }
    @Bean
    public Queue notificationQueue(){
        return new Queue(this.notificationQueue);
    }
    @Bean
    public Binding internalToNotificationBinding(){

        return BindingBuilder
                .bind(notificationQueue())
                .to(internalTopicExchange())
                .with(this.internalNotificationRoutingKey);
    }




}
