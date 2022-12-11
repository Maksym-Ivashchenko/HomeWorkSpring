package ua.goit.spring.model.dao;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "roles")
public class RoleDao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "role_name", length = 50)
    private String name;

    @ManyToMany(mappedBy = "roles")
    @ToString.Exclude
    private Set<UserDao> users;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        RoleDao roleDao = (RoleDao) o;
        return id != null && Objects.equals(id, roleDao.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
