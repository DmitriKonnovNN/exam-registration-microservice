package io.dmitrikonnov.customer.external.config;

import feign.Feign;
import feign.Logger;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.querymap.BeanQueryMapEncoder;
import feign.querymap.FieldQueryMapEncoder;
import feign.slf4j.Slf4jLogger;
import io.dmitrikonnov.customer.external.inventory.InventoryServiceClient;
import io.dmitrikonnov.customer.external.session.UserSessionClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {
    @Bean
    public UserSessionClient userSessionClient(){
        return Feign.builder()
                .logger(new Slf4jLogger())
                .logLevel(Logger.Level.FULL)
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .queryMapEncoder(new FieldQueryMapEncoder()) // this is default along with BeanQueryMapEncoder
                .target(UserSessionClient.class, "http://localhost:8089");

    }

    @Bean
    public InventoryServiceClient inventoryServiceClient(){
        return Feign.builder()
                .logger(new Slf4jLogger())
                .logLevel(Logger.Level.FULL)
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .target(InventoryServiceClient.class, "http://localhost:8090");
    }
}
