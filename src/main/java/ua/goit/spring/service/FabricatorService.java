package ua.goit.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.goit.spring.model.dao.FabricatorDao;
import ua.goit.spring.model.dto.FabricatorDto;
import ua.goit.spring.repository.FabricatorRepository;
import ua.goit.spring.service.converter.FabricatorConverter;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FabricatorService implements IService<FabricatorDto, UUID> {
    private final FabricatorRepository fabricatorRepository;
    private final FabricatorConverter fabricatorConverter;

    @Override
    public List<FabricatorDto> findAll() {
        return fabricatorRepository.findAll()
                .stream()
                .map(fabricatorConverter::from)
                .collect(Collectors.toList());
    }

    @Override
    public FabricatorDto save(FabricatorDto entity) {
        FabricatorDao fabricatorDao = fabricatorRepository.save(fabricatorConverter.to(entity));
        return fabricatorConverter.from(fabricatorDao);
    }

    @Override
    public FabricatorDto getById(UUID id) {
        FabricatorDao fabricatorDao = fabricatorRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return fabricatorConverter.from(fabricatorDao);
    }

    @Override
    public void deleteById(UUID id) {
        fabricatorRepository.deleteById(id);
    }

    public FabricatorDto findByName(String name) {
        FabricatorDao byName = fabricatorRepository.findByName(name);
        return fabricatorConverter.from(byName);
    }
}
