package ua.goit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.goit.spring.model.dao.FabricatorDao;

import java.util.UUID;

@Repository
public interface FabricatorRepository extends JpaRepository<FabricatorDao, UUID> {

    @Query("FROM FabricatorDao f WHERE f.name LIKE :name")
    FabricatorDao findByName(String name);
}
