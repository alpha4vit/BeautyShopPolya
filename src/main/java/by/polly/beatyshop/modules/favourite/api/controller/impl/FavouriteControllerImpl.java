package by.polly.beatyshop.modules.favourite.api.controller.impl;

import by.polly.beatyshop.modules.favourite.api.dto.Favourite;
import by.polly.beatyshop.modules.favourite.api.dto.ProductAddRequest;
import by.polly.beatyshop.modules.favourite.service.FavouriteService;
import by.polly.beatyshop.modules.favourite.service.mapper.FavouriteMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/favourites")
@RestController
@RequiredArgsConstructor
public class FavouriteControllerImpl {

    private final FavouriteService favouriteService;
    private final FavouriteMapper favouriteMapper;

    @GetMapping("/{userId}")
    public ResponseEntity<Favourite> getById(@PathVariable("userId") Long userId) {
        final var product = favouriteService.getByUserId(userId);
        return ResponseEntity.ok(favouriteMapper.toDTO(product));
    }

    @PostMapping(value = "/{userId}")
    public ResponseEntity<Favourite> addProductToFavourite(@PathVariable("userId") Long userId, @RequestBody ProductAddRequest productAddRequest) {
        var res = favouriteService.addToFavourite(userId, productAddRequest);
        return ResponseEntity.ok(favouriteMapper.toDTO(res));
    }

    @DeleteMapping(value = "/{userId}/{productId}")
    public ResponseEntity<Favourite> removeProductFromFavourite(@PathVariable("userId") Long userId, @PathVariable("productId") Long productId) {
        var res = favouriteService.removeFromFavourite(userId, productId);
        return ResponseEntity.ok(favouriteMapper.toDTO(res));
    }
}
