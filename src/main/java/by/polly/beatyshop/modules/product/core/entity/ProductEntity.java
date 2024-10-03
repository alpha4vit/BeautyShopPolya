package by.polly.beatyshop.modules.product.core.entity;

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

    @ManyToOne
    @JoinColumn(name = "measurement_type_id", referencedColumnName = "id")
    private MeasurementTypeEntity measurementType;

    private String units;

    @ManyToOne
    @JoinColumn(name = "product_category_id", referencedColumnName = "id")
    private ProductCategoryEntity category;

    @OneToMany(mappedBy = "product")
    private List<ProductImageEntity> images;

}
