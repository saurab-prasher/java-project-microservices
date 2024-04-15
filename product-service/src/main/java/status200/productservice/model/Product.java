package status200.productservice.model;

import lombok.Data;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Products")
@Data // Generates getters, setters, toString, equals, and hashCode methods
public class Product {
    @Getter
    @Id
    private String id;

    private String name;
    private String description;
    private Double price;
    private Integer stock; // Changed from stockAvailable to stock
    private String imageId; // This will hold the filename or path to the image

    // Getters and Setters or use Lombok for brevity
}