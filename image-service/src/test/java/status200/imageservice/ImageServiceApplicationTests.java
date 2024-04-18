package status200.imageservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

// Annotating this class with @SpringBootApplication to mark it as a Spring Boot application
@SpringBootApplication
// Annotating with @EnableDiscoveryClient to enable service discovery for this application
@EnableDiscoveryClient
public class ImageServiceApplication {

    // Main method, the entry point of the application
    public static void main(String[] args) {
        // Start the Spring Boot application
        SpringApplication.run(ImageServiceApplication.class, args);
    }

}
