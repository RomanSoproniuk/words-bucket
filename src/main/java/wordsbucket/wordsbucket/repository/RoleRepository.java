package wordsbucket.wordsbucket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wordsbucket.wordsbucket.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
