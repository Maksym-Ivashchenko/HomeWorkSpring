package ua.goit.spring.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.goit.spring.model.dao.RoleDao;

import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<RoleDao, UUID> {

    @Query("FROM RoleDao r WHERE r.name = 'user'")
    RoleDao getUserRole();
}
