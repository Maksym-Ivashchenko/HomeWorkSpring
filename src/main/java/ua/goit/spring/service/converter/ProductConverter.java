package ua.goit.spring.service.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.goit.spring.model.dao.ProductDao;
import ua.goit.spring.model.dto.ProductDto;

@RequiredArgsConstructor
@Service
public class ProductConverter implements Converter<ProductDto, ProductDao> {
    private final FabricatorConverter fabricatorConverter;
    @Override
    public ProductDto from(ProductDao entity) {
        ProductDto productDto = new ProductDto();
        productDto.setId(entity.getId());
        productDto.setName(entity.getName());
        productDto.setPrice(entity.getPrice());
        productDto.setFabricatorDto(fabricatorConverter.from(entity.getFabricatorDao()));
        return productDto;
    }

    @Override
    public ProductDao to(ProductDto entity) {
        ProductDao productDao = new ProductDao();
        productDao.setId(entity.getId());
        productDao.setName(entity.getName());
        productDao.setPrice(entity.getPrice());
        productDao.setFabricatorDao(fabricatorConverter.to(entity.getFabricatorDto()));
        return productDao;
    }
}
