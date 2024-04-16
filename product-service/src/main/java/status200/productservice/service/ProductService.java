package status200.productservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

import org.springframework.stereotype.Service;
import status200.productservice.model.Product;
import status200.productservice.repository.ProductRepository;

import java.util.List;
import java.util.Optional;


@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Optional<Product> getProduct(String id) {
        return productRepository.findById(id);
    }

    public List<Product> getAllProducts() {
//        productRepository.findAll().forEach(item -> System.out.println(item.getName()));
        return productRepository.findAll();
    }


    @Autowired
    public ProductService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public Product getProductById(String id) {
        // Send a request to get product by ID
        kafkaTemplate.send("get-product", id);
        // Logic to handle response will be handled by Kafka listener
        return null;
    }

    public void updateProductStock(String id, Integer quantity) {
        // Send a request to update product stock
        String message = id + "," + quantity.toString();
        kafkaTemplate.send("update-product-stock", message);
    }


    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }

    public Product updateProduct(String id, Product productDetails) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        product.setName(productDetails.getName());
        product.setDescription(productDetails.getDescription());
        product.setPrice(productDetails.getPrice());
        product.setImageId(productDetails.getImageId());
        product.setStock(productDetails.getStock());
        return productRepository.save(product);
    }


    @KafkaListener(topics = "get-product-response", groupId = "order-service-group", containerFactory = "kafkaListenerContainerFactory")
    public void getProductResponse(String productJson) {
        // Parse productJson to Product object and return
    }

    @KafkaListener(topics = "get-all-products-response", groupId = "order-service-group", containerFactory = "kafkaListenerContainerFactory")
    public void getAllProductsResponse(List<Product> products) {
        // Handle the list of products
    }

    @KafkaListener(topics = "create-product-response", groupId = "order-service-group", containerFactory = "kafkaListenerContainerFactory")
    public void createProductResponse(Product product) {
        // Handle the created product
    }

    @KafkaListener(topics = "update-product-response", groupId = "order-service-group", containerFactory = "kafkaListenerContainerFactory")
    public void updateProductResponse(Product product) {
        // Handle the updated product
    }


}