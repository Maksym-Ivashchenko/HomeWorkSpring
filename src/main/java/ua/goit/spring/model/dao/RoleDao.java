package ua.goit.spring.model.dao;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@Table(name = "roles")
public class RoleDao {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private final UUID id;

    @Column(name = "name", length = 50)
    private final String name;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private final UserDao user;
}
