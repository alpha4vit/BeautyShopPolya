package by.polly.beatyshop.modules.product.core.entity;

import by.polly.beatyshop.modules.favourite.api.dto.Favourite;
import by.polly.beatyshop.modules.favourite.core.entity.FavouriteEntity;
import by.polly.beatyshop.modules.order.api.dto.Order;
import by.polly.beatyshop.modules.order.core.entity.OrderEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private Long roomCount;

    private Double originalPrice;

    @Column(name = "original_currency")
    private String originalCurrency;

    private Long square;

    private Long floor;

    private Long userId;

    @Column(columnDefinition = "text")
    private String options;

    @Column(name = "contact_number")
    private String contactNumber;

    @ManyToOne
    @JoinColumn(name = "product_category_id", referencedColumnName = "id")
    private ProductCategoryEntity category;

    @Column(columnDefinition = "text")
    private String images;

    @OneToMany
    private List<OrderEntity> orders;

    @ManyToOne
    @JoinColumn(name = "city_id", referencedColumnName = "id")
    private City city;

    private String address;
}
