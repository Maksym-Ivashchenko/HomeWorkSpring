package ua.goit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.goit.spring.model.dao.UserDao;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserDao, UUID> {
}
