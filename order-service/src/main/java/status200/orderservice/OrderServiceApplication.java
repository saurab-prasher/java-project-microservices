package status200.orderservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@EnableDiscoveryClient
@EnableMongoRepositories
@SpringBootApplication(scanBasePackages = {"status200.orderservice", "status200.productservice"})
public class OrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}
}