package by.polly.beatyshop.modules.favourite.core.repository;

import by.polly.beatyshop.modules.favourite.core.entity.FavouriteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavouriteRepository extends JpaRepository<FavouriteEntity, Long> {
    List<FavouriteEntity> findAllByUserId(Long userId);
}
