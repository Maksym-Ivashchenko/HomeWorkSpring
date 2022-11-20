package ua.goit.spring.model.dao;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@Table(name = "fabricators")
public class FabricatorDao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final UUID id;

    @Column(name = "name", length = 50)
    private final String name;


    private final Set<ProductDao> products;
}
