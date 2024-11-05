package by.polly.beatyshop.modules.order.api.controller.impl;

import by.polly.beatyshop.modules.order.api.controller.OrderController;
import by.polly.beatyshop.modules.order.api.dto.Order;
import by.polly.beatyshop.modules.order.api.dto.ProductAddRequest;
import by.polly.beatyshop.modules.order.core.entity.OrderEntity;
import by.polly.beatyshop.modules.order.service.OrderService;
import by.polly.beatyshop.modules.order.service.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class OrderControllerImpl implements OrderController {

    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @Override
    public ResponseEntity<Order> getById(Long id) {
        final var product = orderService.getById(id);
        return ResponseEntity.ok(orderMapper.toDTO(product));
    }

    @Override
    public ResponseEntity<Order> save(Order dto) {
        final var saved = orderService.save(orderMapper.toEntity(dto));
        return ResponseEntity.ok(orderMapper.toDTO(saved));
    }

    @Override
    public ResponseEntity<Order> addProductToOrder(Long orderId, ProductAddRequest productAddRequest) {
        final OrderEntity res = null;
        return ResponseEntity.ok(orderMapper.toDTO(res));
    }

    @Override
    public ResponseEntity<List<Order>> getAll() {
        var orders = orderService.getAll();
        return ResponseEntity.ok(orderMapper.toDTOs(orders));
    }

    @Override
    public void deleteById(Long orderId) {
        orderService.deleteById(orderId);
    }

    @Override
    public ResponseEntity<List<Order>> getAllByUserId(Long userId) {
        var orders = orderService.getAllByUserId(userId);
        return ResponseEntity.ok(orderMapper.toDTOs(orders));
    }

}
