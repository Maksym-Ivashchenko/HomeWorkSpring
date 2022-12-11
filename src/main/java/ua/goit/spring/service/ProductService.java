package ua.goit.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.goit.spring.model.dao.ProductDao;
import ua.goit.spring.model.dto.ProductDto;
import ua.goit.spring.repository.ProductRepository;
import ua.goit.spring.service.converter.ProductConverter;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductService implements IService<ProductDto, UUID> {
    private final ProductRepository productRepository;
    private final ProductConverter productConverter;
    @Override
    public List<ProductDto> findAll() {
        return productRepository.findAll()
                .stream()
                .map(productConverter::from)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto save(ProductDto entity) {
        ProductDao productDao = productRepository.save(productConverter.to(entity));
        return productConverter.from(productDao);
    }

    @Override
    public ProductDto getById(UUID id) {
        ProductDao productDao = productRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return productConverter.from(productDao);
    }

    @Override
    public void deleteById(UUID id) {
        productRepository.deleteById(id);
    }
}
