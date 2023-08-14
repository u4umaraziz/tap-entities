package com.tap.entities.tapentities.data;

import com.tap.entities.tapentities.model.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntityRepository extends JpaRepository<BrandEntity, Long> {

}
