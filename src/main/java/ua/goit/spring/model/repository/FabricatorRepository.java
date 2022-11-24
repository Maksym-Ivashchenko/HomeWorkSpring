package ua.goit.spring.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.goit.spring.model.dao.FabricatorDao;

import java.util.UUID;

@Repository
public interface FabricatorRepository extends JpaRepository<FabricatorDao, UUID> {
}
