package by.polly.beatyshop.modules.product.core.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

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

    private String applying;

    private Double originalPrice;

    @Column(name = "original_currency")
    private String originalCurrency;

    private String size;

    private String units;

    private Long userId;

    @Column(columnDefinition = "text")
    private String options;

    @ManyToOne
    @JoinColumn(name = "product_category_id", referencedColumnName = "id")
    private ProductCategoryEntity category;

    @Column(columnDefinition = "text")
    private String images;

}
