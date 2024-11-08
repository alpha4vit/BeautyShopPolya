package by.polly.beatyshop.modules.favourite.service.mapper;


import by.polly.beatyshop.modules.favourite.api.dto.Favourite;
import by.polly.beatyshop.modules.favourite.core.entity.FavouriteEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface FavouriteMapper {

    Favourite toDTO(FavouriteEntity entity);

    FavouriteEntity toEntity(Favourite dto);

    List<Favourite> toDTOs(List<FavouriteEntity> products);

    List<FavouriteEntity> toEntities(List<Favourite> dtos);

}
