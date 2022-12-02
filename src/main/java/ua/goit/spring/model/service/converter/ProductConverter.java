package ua.goit.spring.model.service.converter;

import org.springframework.stereotype.Service;
import ua.goit.spring.model.dao.ProductDao;
import ua.goit.spring.model.dto.ProductDto;

@Service
public class ProductConverter implements Converter<ProductDto, ProductDao> {
    @Override
    public ProductDto from(ProductDao entity) {
        ProductDto productDto = new ProductDto();
        productDto.setId(entity.getId());
        productDto.setName(entity.getName());
        productDto.setPrice(entity.getPrice());
        return productDto;
    }

    @Override
    public ProductDao to(ProductDto entity) {
        ProductDao productDao = new ProductDao();
        productDao.setId(entity.getId());
        productDao.setName(entity.getName());
        productDao.setPrice(entity.getPrice());
        return productDao;
    }
}
