package by.polly.beatyshop.modules.product.service.impl;

import by.polly.beatyshop.modules.product.core.entity.MeasurementTypeEntity;
import by.polly.beatyshop.modules.product.core.repository.MeasurementTypeRepository;
import by.polly.beatyshop.modules.product.service.MeasurementTypeService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MeasurementTypeServiceImpl implements MeasurementTypeService {

    private final MeasurementTypeRepository measurementTypeRepository;

    @Override
    @Cacheable(value = "measurement_types", key = "#id")
    public MeasurementTypeEntity getById(final Long id) {
        return measurementTypeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Measurement type with this id not found!"));
    }
}
