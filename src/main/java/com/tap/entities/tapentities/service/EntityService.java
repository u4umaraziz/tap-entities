package com.tap.entities.tapentities.service;

import com.tap.entities.tapentities.data.EntityRepository;
import com.tap.entities.tapentities.model.BrandEntity;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class EntityService {

    EntityRepository entityRepository;

    public List<BrandEntity> getAllEntities() {
        return entityRepository.findAll();
    }

    public BrandEntity createEntity(final BrandEntity brandEntity) {
        return entityRepository.save(brandEntity);
    }

    public BrandEntity getEntityById(final Long id) {
        Optional<BrandEntity> entity = entityRepository.findById(id);
        if (entity.isPresent()) {
            return entity.get();
        } else {
            return null;
        }
    }

    public BrandEntity updateEntity(final Long id, final BrandEntity updatedBrandEntity) {
        Optional<BrandEntity> entity = entityRepository.findById(id);
        if (entity.isPresent()) {
            BrandEntity existingBrandEntity = entity.get();
            return entityRepository.save(existingBrandEntity);
        } else {
            return null;
        }
    }

    public void deleteEntity(final Long id) {
        Optional<BrandEntity> entity = entityRepository.findById(id);
        if (entity.isPresent())
            entityRepository.deleteById(id);
    }
}
