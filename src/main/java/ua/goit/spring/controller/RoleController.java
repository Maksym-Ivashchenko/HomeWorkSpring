package ua.goit.spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.goit.spring.model.dto.RoleDto;
import ua.goit.spring.service.RoleService;

import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/role")
@Controller
public class RoleController {
    private final RoleService roleService;

    @GetMapping("/list")
    public ModelAndView list() {
        ModelAndView result = new ModelAndView("roleList");
        return result.addObject("roles", roleService.findAll());
    }

    @GetMapping("/save")
    public ModelAndView saveForm() {
        return new ModelAndView("roleSave");
    }

    @PostMapping("/save")
    public synchronized ModelAndView save(@RequestParam(name = "roleId") String id,
                                          @RequestParam(name = "name") String name) {
        ModelAndView result = new ModelAndView("roleSave");
        RoleDto roleDto = new RoleDto();
        try {
            if (!id.isEmpty()) {
                roleDto.setId(UUID.fromString(id));
            }
            roleDto.setName(name);
            if (roleDto.getName().isBlank()) {
                return result.addObject("message", "Role not created");
            } else {
                return result.addObject("role", roleService.save(roleDto));
            }
        } catch (IllegalArgumentException e) {
            return result.addObject("message", "Role not created");
        }
    }

    @GetMapping("/search")
    public ModelAndView getByIdForm() {
        return new ModelAndView("roleGetById");
    }

    @PostMapping("/search")
    public ModelAndView getById(@RequestParam(name = "roleId") String id) {
        ModelAndView result = new ModelAndView("roleGetById");
        try {
            if (id == null || id.equals("")) {
                return result.addObject("message", "Role not found");
            } else {
                return result.addObject("role", roleService.getById(UUID.fromString(id)));
            }
        } catch (IllegalArgumentException e) {
            return result.addObject("message", "Role not found");
        }
    }

    @GetMapping("/delete")
    public ModelAndView deleteByIdForm() {
        return new ModelAndView("roleDelete");
    }

    @PostMapping("/delete")
    public ModelAndView deleteById(@RequestParam("roleId") String id) {
        ModelAndView result = new ModelAndView("roleDelete");
        try {
            if (id.isEmpty() || id.isBlank()) {
                return result.addObject("message", "Role not found");
            } else {
                roleService.deleteById(UUID.fromString(id));
                return result.addObject("message", "Role successfully deleted");
            }
        } catch (IllegalArgumentException | IncorrectResultSizeDataAccessException e) {
            return result.addObject("message", "Role not found");
        }
    }

}
