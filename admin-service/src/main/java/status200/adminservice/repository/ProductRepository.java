package status200.adminservice.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import status200.adminservice.model.Product;

public interface ProductRepository extends MongoRepository<Product, String> {
}