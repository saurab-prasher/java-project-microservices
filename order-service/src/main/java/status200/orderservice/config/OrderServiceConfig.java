package status200.orderservice.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import status200.productservice.service.ProductService;

@Configuration
public class OrderServiceConfig {

    @Bean
    public ProductService productService(KafkaTemplate<String, String> kafkaTemplate) {
        return new ProductService(kafkaTemplate);
    }
}
