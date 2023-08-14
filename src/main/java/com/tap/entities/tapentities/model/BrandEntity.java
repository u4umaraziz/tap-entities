package com.tap.entities.tapentities.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Builder(toBuilder = true)
@Data
@Entity
@Table(name = "brand_entity")
public class BrandEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String license;
    @Column(name = "tax_id")
    private String taxId;

}