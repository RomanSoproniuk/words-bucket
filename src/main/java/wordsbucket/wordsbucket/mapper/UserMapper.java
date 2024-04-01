package wordsbucket.wordsbucket.mapper;

import org.mapstruct.Mapper;
import wordsbucket.wordsbucket.config.MapperConfig;
import wordsbucket.wordsbucket.dto.UserResponseDto;
import wordsbucket.wordsbucket.model.User;

@Mapper(config = MapperConfig.class)
public interface UserMapper {
    UserResponseDto toDto(User user);
}
