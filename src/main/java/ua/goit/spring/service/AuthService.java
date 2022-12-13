package ua.goit.spring.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthService {

    public ModelAndView getSecurityResponse() {
        Set<String> userRoles = getUserRoles();
            if (userRoles.contains("ADMIN")) {
                return new ModelAndView("/indexAdmin");
            }
        return new ModelAndView("/index");
    }

    private Set<String> getUserRoles() {
        return SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .map(it -> it.replace("ROLE_", ""))
                .collect(Collectors.toSet());
    }
}
