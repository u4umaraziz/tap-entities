package com.tap.entities.tapentities.controller;

import com.tap.entities.tapentities.model.BrandEntity;
import com.tap.entities.tapentities.service.EntityService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/entities")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class EntityController {

    EntityService entityService;

    // GET endpoint to retrieve all entities
    @GetMapping
    public List<BrandEntity> getAllEntities() {
        return entityService.getAllEntities();
    }

    // POST endpoint to create a new entity
    @PostMapping
    public BrandEntity createEntity(@RequestBody BrandEntity brandEntity) {
        return entityService.createEntity(brandEntity);
    }

    // GET endpoint to retrieve a single entity by ID
    @GetMapping("/{id}")
    public ResponseEntity<BrandEntity> getEntityById(@PathVariable Long id) {
        BrandEntity brandEntity = entityService.getEntityById(id);
        if (!Objects.isNull(brandEntity)) {
            return ResponseEntity.ok(brandEntity);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // PUT endpoint to update an existing entity
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateEntity(@PathVariable Long id, @RequestBody BrandEntity updatedBrandEntity) {
        entityService.updateEntity(id, updatedBrandEntity);
        return ResponseEntity.ok(null);
    }

    // DELETE endpoint to delete an entity
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEntity(@PathVariable Long id) {
        return ResponseEntity.ok(null);
    }
}
