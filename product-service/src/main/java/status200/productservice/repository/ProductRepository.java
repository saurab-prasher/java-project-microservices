package status200.productservice.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import status200.productservice.model.Product;
@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
}