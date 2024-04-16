package status200.adminservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import status200.adminservice.model.User;

public interface UserRepository extends MongoRepository<User, String> {
    User findByUsername(String username);
}