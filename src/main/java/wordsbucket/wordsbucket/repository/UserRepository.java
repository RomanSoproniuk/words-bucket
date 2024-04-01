package wordsbucket.wordsbucket.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import wordsbucket.wordsbucket.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM users u LEFT JOIN FETCH u.roles WHERE u.email=:email")
    Optional<User> findByEmail(String email);
}
