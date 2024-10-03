package by.polly.beatyshop.modules.user.mapper;

import by.polly.beatyshop.modules.user.dto.User;
import by.polly.beatyshop.modules.user.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    UserEntity toEntity(User dto);

    User toDTO(UserEntity entity);

    List<UserEntity> toEntities(List<User> dtos);

    List<User> toDTOs(List<UserEntity> entities);

}
