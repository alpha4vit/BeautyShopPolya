package by.polly.beatyshop.modules.product.service;

import by.polly.beatyshop.modules.product.api.dto.ProductCategoryFilterDto;
import by.polly.beatyshop.modules.product.core.entity.ProductEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {

    ProductEntity getById(Long id);

    List<ProductEntity> getAllByUserId(Long userId);

    List<ProductEntity> getAll(ProductCategoryFilterDto filter);

    List<ProductEntity> getAll();

    ProductEntity save(Long userId, ProductEntity product);

    List<ProductEntity> getAllByCategoryId(Long categoryId);

    List<String> uploadImages(Long productId, MultipartFile image);

    void deleteById(Long id);

}
