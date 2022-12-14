package ua.goit.spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import ua.goit.spring.service.AuthService;
import ua.goit.spring.service.SecurityResponse;

@RequiredArgsConstructor
@RestController
public class SecurityController {
    private final AuthService authService;

    @GetMapping("/index")
    public ModelAndView get() {
        return authService.getSecurityResponse();
    }
}
