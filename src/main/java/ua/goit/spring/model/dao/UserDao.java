package ua.goit.spring.model.dao;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@Table(name = "users")
public class UserDao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column(name = "name", length = 50)
    private String login;

    @Column(name = "password", length = 50)
    private String password;

    @Column(name = "first_name", length = 200)
    private String firstName;

    @Column(name = "last_name", length = 200)
    private String lastName;

//    @OneToOne(mappedBy = "userDao", cascade = CascadeType.ALL)
//    @PrimaryKeyJoinColumn
//    private Set<RoleDao> roles;
}
