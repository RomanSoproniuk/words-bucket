package wordsbucket.wordsbucket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wordsbucket.wordsbucket.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
