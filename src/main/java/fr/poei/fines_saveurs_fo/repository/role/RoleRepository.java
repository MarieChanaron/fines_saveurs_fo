package fr.poei.fines_saveurs_fo.repository.role;

import fr.poei.fines_saveurs_fo.entity.role.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional <Role> findByName(String name);


}
