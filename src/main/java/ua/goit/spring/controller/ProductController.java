package ua.goit.spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.goit.spring.model.dto.ProductDto;
import ua.goit.spring.model.service.ProductService;

import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/product")
@Controller
public class ProductController {
    private final ProductService productService;

    @GetMapping("/list")
    public ModelAndView list() {
        ModelAndView result = new ModelAndView("productList");
        return result.addObject("products", productService.findAll());
    }

    @GetMapping("/save")
    public ModelAndView saveForm() {
        return new ModelAndView("productSave");
    }

    @PostMapping("/save")
    public synchronized ModelAndView save(@RequestParam(name = "productId") String id,
                                          @RequestParam(name = "name") String name,
                                          @RequestParam(name = "price") String price) {
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
            if (productDto.getName().isBlank() || productDto.getPrice() == null) {
                return result.addObject("message", "Product not created");
            } else {
                result.addObject("product", productService.save(productDto));
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

}
