package ua.goit.spring.service.converter;

import org.springframework.stereotype.Service;
import ua.goit.spring.model.dao.FabricatorDao;
import ua.goit.spring.model.dto.FabricatorDto;

@Service
public class FabricatorConverter implements Converter<FabricatorDto, FabricatorDao> {
    @Override
    public FabricatorDto from(FabricatorDao entity) {
        FabricatorDto fabricatorDto = new FabricatorDto();
        fabricatorDto.setId(entity.getId());
        fabricatorDto.setName(entity.getName());
        return fabricatorDto;
    }

    @Override
    public FabricatorDao to(FabricatorDto entity) {
        FabricatorDao fabricatorDao = new FabricatorDao();
        fabricatorDao.setId(entity.getId());
        fabricatorDao.setName(entity.getName());
        return fabricatorDao;
    }
}
