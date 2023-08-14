package com.tap.entities.tapentities.service;

import com.tap.entities.tapentities.data.EntityRepository;
import com.tap.entities.tapentities.model.BrandEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class EntityServiceTests {

    @InjectMocks
    EntityService entityService;

    @Mock
    EntityRepository entityRepository;


    @Test
    public void checkBrandEntityExists() {

        BrandEntity mockedResponse = BrandEntity.builder()
                .id(1L)
                .taxId("TX-112")
                .name("Test Entity")
                .build();

        // Mock the repository's behavior
        when(entityRepository.findById(anyLong())).thenReturn(Optional.of(mockedResponse));

        // Call the service method
        BrandEntity brandEntity = entityService.getEntityById(anyLong());

        // Verify the repository method was called with the expected argument
        verify(entityRepository).findById(anyLong());

        // assert equal to check returned brand entity name is equal to expected entity
        Assertions.assertEquals(brandEntity.getName() , "Test Entity");
    }
}
