package io.dmitrikonnov.fraudcheck;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication
@EnableEurekaClient
@PropertySources({@PropertySource("classpath:clients-${spring.profiles.active}.properties")})
public class FraudcheckApplication {
    public static void main(String[] args) {
        SpringApplication.run(FraudcheckApplication.class, args);}
}
