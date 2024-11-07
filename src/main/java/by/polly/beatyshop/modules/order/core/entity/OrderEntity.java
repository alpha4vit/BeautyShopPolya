package by.polly.beatyshop.modules.order.core.entity;

import by.polly.beatyshop.modules.product.api.dto.Product;
import by.polly.beatyshop.modules.product.core.entity.ProductEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.ORDINAL)
    @Column(name = "order_state")
    private OrderState orderState;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "sum_price")
    private Double sumPrice;

    @ManyToMany
    @JoinTable(
            joinColumns = @JoinColumn(name = "order_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id")
    )
    private List<ProductEntity> products;

}
