package by.polly.beatyshop.modules.product.core.repository;

import by.polly.beatyshop.modules.product.core.entity.MeasurementTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasurementTypeRepository extends JpaRepository<MeasurementTypeEntity, Long> {
}
