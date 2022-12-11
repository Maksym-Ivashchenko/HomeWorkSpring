package ua.goit.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.goit.spring.model.dao.RoleDao;
import ua.goit.spring.model.dto.RoleDto;
import ua.goit.spring.repository.RoleRepository;
import ua.goit.spring.service.converter.RoleConverter;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RoleService implements IService<RoleDto, UUID> {
    private final RoleRepository roleRepository;
    private final RoleConverter roleConverter;
    @Override
    public List<RoleDto> findAll() {
        return roleRepository.findAll()
                .stream()
                .map(roleConverter::from)
                .collect(Collectors.toList());
    }

    @Override
    public RoleDto save(RoleDto entity) {
        RoleDao roleDao = roleRepository.save(roleConverter.to(entity));
        return roleConverter.from(roleDao);
    }

    @Override
    public RoleDto getById(UUID id) {
        RoleDao roleDao = roleRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return roleConverter.from(roleDao);
    }

    @Override
    public void deleteById(UUID id) {
        roleRepository.deleteById(id);
    }

    public RoleDto getUserRole() {
        return roleConverter.from(roleRepository.getUserRole());
    }
}
