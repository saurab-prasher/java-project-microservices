package status200.productservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import status200.productservice.model.Product;
import status200.productservice.service.ProductService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*") // Enable CORS for all endpoints in this controller
public class ProductController {

    @Autowired
    private ProductService productService;

    // Endpoint to create a new product
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return ResponseEntity.ok(productService.createProduct(product));
    }

    // Endpoint to get a product by its ID
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Product>> getProductById(@PathVariable String id) {
        return ResponseEntity.ok(productService.getProduct(id));
    }

    // Endpoint to get all products
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    // Endpoint to delete a product by its ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    // Endpoint to update a product by its ID
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable String id, @RequestBody Product productDetails) {
        return productService.updateProduct(id, productDetails);
    }

}
