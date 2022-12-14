package ua.goit.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class MyUserJdbcDetailsService implements UserDetailsService {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<UserDetails> userData = jdbcTemplate.query(
                "SELECT user_name, password, role_name\n" +
                        " FROM users AS u\n" +
                        " INNER JOIN users_roles AS ur ON u.id = ur.user_id\n" +
                        " INNER JOIN roles AS r ON r.id = ur.role_id\n" +
                        " WHERE user_name = :userName",
                Map.of("userName", username),
                new UserDataRowMapper()
        );
        return userData.isEmpty() ? null : userData.get(0);
    }

    private static class UserDataRowMapper implements RowMapper<UserDetails> {

        @Override
        public UserDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
            String role = rs.getString("role_name");
            String userName = rs.getString("user_name");
            String password = rs.getString("password");

            return new UserDetails() {
                @Override
                public Collection<? extends GrantedAuthority> getAuthorities() {
                    return Collections.singleton(() -> "ROLE_" + role);
                }

                @Override
                public String getPassword() {
                    return password;
                }

                @Override
                public String getUsername() {
                    return userName;
                }

                @Override
                public boolean isAccountNonExpired() {
                    return true;
                }

                @Override
                public boolean isAccountNonLocked() {
                    return true;
                }

                @Override
                public boolean isCredentialsNonExpired() {
                    return true;
                }

                @Override
                public boolean isEnabled() {
                    return true;
                }
            };
        }
    }
}
