package ua.goit.spring.model.dao;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@Table(name = "fabricators")
public class FabricatorDao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "name", length = 50)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fabricatorDao")
    private Set<ProductDao> products;
}
