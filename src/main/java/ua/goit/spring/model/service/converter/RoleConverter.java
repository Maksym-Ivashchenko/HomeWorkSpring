package ua.goit.spring.model.service.converter;

import org.springframework.stereotype.Service;
import ua.goit.spring.model.dao.RoleDao;
import ua.goit.spring.model.dto.RoleDto;

@Service
public class RoleConverter implements Converter<RoleDto, RoleDao> {
    @Override
    public RoleDto from(RoleDao entity) {
        RoleDto roleDto = new RoleDto();
        roleDto.setId(entity.getId());
        roleDto.setName(entity.getName());
        return roleDto;
    }

    @Override
    public RoleDao to(RoleDto entity) {
        RoleDao roleDao = new RoleDao();
        roleDao.setId(entity.getId());
        roleDao.setName(entity.getName());
        return roleDao;
    }
}
