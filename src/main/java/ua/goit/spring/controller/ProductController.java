package ua.goit.spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.goit.spring.model.dto.FabricatorDto;
import ua.goit.spring.model.dto.ProductDto;
import ua.goit.spring.service.FabricatorService;
import ua.goit.spring.service.ProductService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RequestMapping("/product")
@Controller
public class ProductController {
    private final ProductService productService;
    private final FabricatorService fabricatorService;

    @GetMapping("/list")
    public ModelAndView list() {
        ModelAndView result = new ModelAndView("productList");
        return result.addObject("products", productService.findAll());
    }

    @GetMapping("/save")
    public ModelAndView saveForm() {
        List<String> listFabricatorsName = getFabricatorsName();
        ModelAndView result = new ModelAndView("productSave");
        return result.addObject("fabricators", listFabricatorsName);
    }

    @PostMapping("/save")
    public synchronized ModelAndView save(@RequestParam(name = "productId") String id,
                                          @RequestParam(name = "name") String name,
                                          @RequestParam(name = "price") String price,
                                          @RequestParam(name = "fabricatorName") String fabricatorName) {

        ModelAndView result = new ModelAndView("productSave");
        ProductDto productDto = new ProductDto();

        try {
            if (!id.isEmpty()) {
                productDto.setId(UUID.fromString(id));
            }
            productDto.setName(name);
            try {
                productDto.setPrice(Long.parseLong(price));
            } catch (NumberFormatException e) {
                return result.addObject("message", "Product not created");
            }
            productDto.setFabricatorDto(fabricatorService.findByName(fabricatorName));
            List<String> listFabricatorsName = getFabricatorsName();
            if (productDto.getName().isBlank() || productDto.getPrice() == null
                    || productDto.getFabricatorDto().getName().isEmpty()) {
                return result.addObject("message", "Product not created");
            } else {
                result.addObject("product", productService.save(productDto));
                result.addObject("fabricators", listFabricatorsName);
                return result.addObject("message", "Product successfully created");
            }
        } catch (IllegalArgumentException e) {
            return result.addObject("message", "Product not created");
        }
    }

    @GetMapping("/search")
    public ModelAndView getByIdForm() {
        return new ModelAndView("productGetById");
    }

    @PostMapping("/search")
    public ModelAndView getById(@RequestParam(name = "productId") String id) {
        ModelAndView result = new ModelAndView("productGetById");
        try {
            if (id.isEmpty() || id.isBlank()) {
                return result.addObject("message", "Product not found");
            } else {
                return result.addObject("product", productService.getById(UUID.fromString(id)));
            }
        } catch (IllegalArgumentException e) {
            return result.addObject("message", "Product not found");
        }
    }

    @GetMapping("/delete")
    public ModelAndView deleteByIdForm() {
        return new ModelAndView("productDelete");
    }
    @PostMapping("/delete")
    public ModelAndView deleteById(@RequestParam("productId") String id) {
        ModelAndView result = new ModelAndView("productDelete");
        try {
            if (id.isEmpty() || id.isBlank()) {
                return result.addObject("message", "Product not found");
            } else {
                productService.deleteById(UUID.fromString(id));
                return result.addObject("message", "Product successfully deleted");
            }
        } catch (IllegalArgumentException | IncorrectResultSizeDataAccessException e) {
            return result.addObject("message", "Product not found");
        }
    }

    private List<String> getFabricatorsName() {
        List<String> listFabricatorsName = fabricatorService.findAll()
                .stream()
                .map(FabricatorDto::getName)
                .collect(Collectors.toList());
        return listFabricatorsName;
    }
}
