package io.dmitrikonnov;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class apiGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(apiGatewayApplication.class, args);
    }
}
