package by.polly.beatyshop.modules.favourite.service.impl;

import by.polly.beatyshop.modules.favourite.core.entity.FavouriteEntity;
import by.polly.beatyshop.modules.product.service.ProductService;
import by.polly.beatyshop.modules.favourite.api.dto.ProductAddRequest;
import by.polly.beatyshop.modules.favourite.core.repository.FavouriteRepository;
import by.polly.beatyshop.modules.favourite.service.FavouriteService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FavouriteServiceImpl implements FavouriteService {

    private final FavouriteRepository favouriteRepository;
    private final ProductService productService;

    @Override
    public FavouriteEntity addToFavourite(final Long userId, final ProductAddRequest request) {
        var product = productService.getById(request.productId());
        var carts = favouriteRepository.findAllByUserId(userId);

        if (carts.isEmpty()) {
            return save(FavouriteEntity.builder()
                    .sumPrice(product.getOriginalPrice())
                    .products(List.of(product))
                    .userId(userId)
                    .build());
        }

        var favourite = carts.getFirst();
        favourite.getProducts().add(product);
        favourite.setSumPrice(favourite.getSumPrice() + product.getOriginalPrice());

        return favouriteRepository.save(favourite);
    }

    @Override
    public FavouriteEntity removeFromFavourite(final Long userId, final Long productId) {
        var favourite = favouriteRepository.findAllByUserId(userId).getFirst();
        favourite.getProducts().removeIf((product) -> product.getId().equals(productId));
        return favouriteRepository.save(favourite);
    }

    @Override
    public FavouriteEntity getByUserId(Long userId) {
        var favourites =  favouriteRepository.findAllByUserId(userId);
        if (favourites.isEmpty())
            throw new EntityNotFoundException(String.format("Favourites for user %s not found", userId.toString()));

        return favourites.getFirst();
    }

    @Override
    public List<FavouriteEntity> getAll() {
        return favouriteRepository.findAll();
    }

    @Override
    public FavouriteEntity save(FavouriteEntity favourite) {
        return favouriteRepository.save(favourite);
    }

    @Override
    public void deleteById(Long id) {
        getByUserId(id);
        favouriteRepository.deleteById(id);
    }

    @Override
    public FavouriteEntity update(Long id, FavouriteEntity favourite) {
        var favouriteForUpdate = getByUserId(id);
        favourite.setId(favouriteForUpdate.getId());
        return favouriteRepository.save(favourite);
    }

    @Override
    public List<FavouriteEntity> getAllByUserId(Long userId) {
        return favouriteRepository.findAllByUserId(userId);
    }
}
