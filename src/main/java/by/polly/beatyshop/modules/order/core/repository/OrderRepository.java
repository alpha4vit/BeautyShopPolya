package by.polly.beatyshop.modules.order.core.repository;

import by.polly.beatyshop.modules.order.core.entity.OrderEntity;
import by.polly.beatyshop.modules.order.core.entity.OrderState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    List<OrderEntity> findAllByUserId(Long userId);
}
