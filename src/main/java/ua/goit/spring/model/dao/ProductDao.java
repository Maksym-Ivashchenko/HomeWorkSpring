package ua.goit.spring.model.dao;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@Table(name = "products")
public class ProductDao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final UUID id;

    @Column(name = "name", length = 50)
    private final String name;

    @Column(name = "name", length = 50)
    private final Long price;

    @OneToOne
    @JoinColumn(name = "fabricator_id", referencedColumnName = "id")
    private final FabricatorDao fabricatorDao;
}
