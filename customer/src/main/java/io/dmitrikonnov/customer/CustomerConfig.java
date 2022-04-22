package io.dmitrikonnov.customer;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableAspectJAutoProxy
@EnableAsync
public class CustomerConfig {
    @LoadBalanced
    @Bean
    RestTemplate template (){
        return new RestTemplate();
    }

}
