package by.polly.beatyshop.modules.favourite.service;


import by.polly.beatyshop.modules.favourite.api.dto.ProductAddRequest;
import by.polly.beatyshop.modules.favourite.core.entity.FavouriteEntity;

import java.util.List;

public interface FavouriteService {

    FavouriteEntity addToFavourite(final Long userId, final ProductAddRequest request);
    FavouriteEntity removeFromFavourite(final Long userId, final Long productId);

    FavouriteEntity getByUserId(Long userId);

    List<FavouriteEntity> getAll();

    FavouriteEntity save(FavouriteEntity favourite);

    void deleteById(Long id);

    FavouriteEntity update(Long id, FavouriteEntity favourite);

    List<FavouriteEntity> getAllByUserId(Long userId);
}
