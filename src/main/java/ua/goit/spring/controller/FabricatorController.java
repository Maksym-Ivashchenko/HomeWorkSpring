package ua.goit.spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.goit.spring.model.dto.FabricatorDto;
import ua.goit.spring.service.FabricatorService;

import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/fabricator")
@RestController
public class FabricatorController {
    private final FabricatorService fabricatorService;

    @GetMapping("/list")
    public ModelAndView list() {
        ModelAndView result = new ModelAndView("fabricatorList");
        return result.addObject("fabricators", fabricatorService.findAll());
    }

    @GetMapping("/save")
    public ModelAndView saveForm() {
        return new ModelAndView("fabricatorSave");
    }

    @PostMapping("/save")
    public synchronized ModelAndView save(@RequestParam(name = "fabricatorId") String id,
                                          @RequestParam(name = "name") String name) {
        ModelAndView result = new ModelAndView("fabricatorSave");
        FabricatorDto fabricatorDto = new FabricatorDto();
        try {
            if (!id.isEmpty()) {
                fabricatorDto.setId(UUID.fromString(id));
            }
            fabricatorDto.setName(name);
            if (fabricatorDto.getName().isBlank()) {
                return result.addObject("message", "Fabricator not created");
            } else {
                return result.addObject("fabricator", fabricatorService.save(fabricatorDto));
            }
        } catch (IllegalArgumentException e) {
            return result.addObject("message", "Fabricator not created");
        }
    }

    @GetMapping("/search")
    public ModelAndView getByIdForm() {
        return new ModelAndView("fabricatorGetById");
    }

    @PostMapping("/search")
    public ModelAndView getById(@RequestParam(name = "fabricatorId") String id) {
        ModelAndView result = new ModelAndView("fabricatorGetById");
        try {
            if (id == null || id.equals("")) {
                return result.addObject("message", "Fabricator not found");
            } else {
                return result.addObject("fabricator", fabricatorService.getById(UUID.fromString(id)));
            }
        } catch (IllegalArgumentException e) {
            return result.addObject("message", "Fabricator not found");
        }
    }

    @GetMapping("/delete")
    public ModelAndView deleteByIdForm() {
        return new ModelAndView("fabricatorDelete");
    }

    @PostMapping("/delete")
    public ModelAndView deleteById(@RequestParam("fabricatorId") String id) {
        ModelAndView result = new ModelAndView("fabricatorDelete");
        try {
            if (id.isEmpty() || id.isBlank()) {
                return result.addObject("message", "Fabricator not found");
            } else {
                fabricatorService.deleteById(UUID.fromString(id));
                return result.addObject("message", "Fabricator successfully deleted");
            }
        } catch (IllegalArgumentException | IncorrectResultSizeDataAccessException e) {
            return result.addObject("message", "Fabricator not found");
        }
    }
}
