package ua.goit.spring.model.dao;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@Table(name = "users")
public class UserDao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final UUID id;

    @Column(name = "name", length = 50)
    private final String login;

    @Column(name = "password", length = 50)
    private final String password;

    @Column(name = "first_name", length = 200)
    private final String firstName;

    @Column(name = "last_name", length = 200)
    private final String lastName;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private final Set<RoleDao> roles;
}
