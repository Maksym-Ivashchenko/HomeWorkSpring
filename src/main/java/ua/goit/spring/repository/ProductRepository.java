package ua.goit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.goit.spring.model.dao.ProductDao;

import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<ProductDao, UUID> {
}
