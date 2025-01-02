package by.polly.beatyshop.modules.order.api.controller.impl;

import by.polly.beatyshop.modules.order.api.dto.Order;
import by.polly.beatyshop.modules.order.api.dto.ProductAddRequest;
import by.polly.beatyshop.modules.order.service.OrderService;
import by.polly.beatyshop.modules.order.service.impl.OrderServiceImpl;
import by.polly.beatyshop.modules.order.service.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/orders")
@RestController
@RequiredArgsConstructor
public class OrderControllerImpl {

    private final OrderService orderService;
    private final OrderMapper orderMapper;
    private final OrderServiceImpl orderServiceImpl;

    @GetMapping("/{userId}")
    public ResponseEntity<List<Order>> getById(@PathVariable("userId") Long userId) {
        final var product = orderService.getByUserId(userId);
        return ResponseEntity.ok(orderMapper.toDTOs(product));
    }

    @GetMapping("/{userId}/completed")
    public ResponseEntity<List<Order>> getCompleted(@PathVariable("userId") Long userId) {
        final var products = orderService.getOrderedByUser(userId);
        return ResponseEntity.ok(orderMapper.toDTOs(products));
    }

    @PostMapping(value = "/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> save(@PathVariable("userId") Long userId, @RequestBody ProductAddRequest productAddRequest) {
        var res = orderService.addToOrder(userId, productAddRequest);
        return ResponseEntity.ok(orderMapper.toDTO(res));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Object> remove(@PathVariable("id") Long id) {
        orderServiceImpl.deleteById(id);
        return ResponseEntity.ok("Oh nice");
    }
}
