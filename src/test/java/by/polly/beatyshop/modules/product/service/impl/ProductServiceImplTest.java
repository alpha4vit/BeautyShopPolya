package by.polly.beatyshop.modules.product.service.impl;

import by.polly.beatyshop.modules.product.api.dto.ProductCategoryFilterDto;
import by.polly.beatyshop.modules.product.core.entity.MeasurementTypeEntity;
import by.polly.beatyshop.modules.product.core.entity.ProductCategoryEntity;
import by.polly.beatyshop.modules.product.core.entity.ProductEntity;
import by.polly.beatyshop.modules.product.core.repository.ProductRepository;
import by.polly.beatyshop.modules.product.service.MeasurementTypeService;
import by.polly.beatyshop.modules.product.service.ProductCategoryService;
import by.polly.beatyshop.modules.product.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ProductServiceImplTest {


        @InjectMocks
        private ProductService productService;

        @Mock
        private ProductRepository productRepository;

        @Mock
        private ProductCategoryService productCategoryService;

        @Mock
        private MeasurementTypeService measurementTypeService;

        @Test
        void testGetById_ProductExists() {
            Long productId = 1L;
            ProductEntity product = new ProductEntity();
            product.setId(productId);

            when(productRepository.findById(productId)).thenReturn(Optional.of(product));

            ProductEntity result = productService.getById(productId);

            assertNotNull(result);
            assertEquals(productId, result.getId());
            verify(productRepository).findById(productId);
        }

        @Test
        void testGetById_ProductNotFound() {
            Long productId = 1L;

            when(productRepository.findById(productId)).thenReturn(Optional.empty());

            Exception exception = assertThrows(EntityNotFoundException.class, () -> {
                productService.getById(productId);
            });

            assertEquals("Product with this id not found!", exception.getMessage());
            verify(productRepository).findById(productId);
        }

        @Test
        void testGetAll_WithFilter() {
            ProductCategoryFilterDto filter = new ProductCategoryFilterDto(BigDecimal.ONE, BigDecimal.ONE, 1L);
            List<ProductEntity> products = Collections.singletonList(new ProductEntity());

            when(productRepository.findAll()).thenReturn(products);

            List<ProductEntity> result = productService.getAll(filter);

            assertEquals(products.size(), result.size());
            verify(productRepository).findAll();
        }

        @Test
        void testGetAll_WithoutFilter() {
            List<ProductEntity> products = Collections.singletonList(new ProductEntity());

            when(productRepository.findAll()).thenReturn(products);

            List<ProductEntity> result = productService.getAll();

            assertEquals(products.size(), result.size());
            verify(productRepository).findAll();
        }

        @Test
        void testSave_Product() {
            ProductEntity product = new ProductEntity();
            product.setId(1L);
            product.setCategory(new ProductCategoryEntity());
            product.setMeasurementType(MeasurementTypeEntity.builder().build());

            when(productCategoryService.getById(any())).thenReturn(product.getCategory());
            when(measurementTypeService.getById(any())).thenReturn(product.getMeasurementType());
            when(productRepository.save(any())).thenReturn(product);

            ProductEntity result = productService.save(product);

            assertNotNull(result);
            assertEquals(product.getId(), result.getId());
            verify(productRepository).save(product);
        }

        @Test
        void testGetAllByCategoryId() {
            Long categoryId = 1L;
            List<ProductEntity> products = Collections.singletonList(new ProductEntity());

            when(productRepository.findAllByCategoryId(categoryId)).thenReturn(products);

            List<ProductEntity> result = productService.getAllByCategoryId(categoryId);

            assertEquals(products.size(), result.size());
            verify(productRepository).findAllByCategoryId(categoryId);
        }
    }