package edu.unbosque.jpa.repositories;
import edu.unbosque.jpa.entities.User;
import java.util.List;
import java.util.Optional;

public interface UserRepository {
    Optional<User> findByFirst_name(String first_name);
    Optional<User> findByLast_name(String last_name);
    Optional<User> findByEmail(String email);

    List<User> findAll();
    Optional<User> save(User user);


}
