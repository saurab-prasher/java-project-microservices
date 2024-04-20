package status200.orderservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

// Annotating with @EnableDiscoveryClient to enable service discovery for this application
@EnableDiscoveryClient
// Annotating with @EnableMongoRepositories to enable MongoDB repositories
@EnableMongoRepositories
// Annotating with @SpringBootApplication to mark it as a Spring Boot application
@SpringBootApplication(scanBasePackages = {"status200.orderservice", "status200.productservice"})
public class OrderServiceApplication {

	// Main method, the entry point of the application
	public static void main(String[] args) {
		// Start the Spring Boot application
		SpringApplication.run(OrderServiceApplication.class, args);
	}
}
