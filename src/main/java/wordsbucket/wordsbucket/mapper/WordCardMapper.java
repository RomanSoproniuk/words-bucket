package wordsbucket.wordsbucket.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import wordsbucket.wordsbucket.config.MapperConfig;
import wordsbucket.wordsbucket.dto.WordCardRequestDto;
import wordsbucket.wordsbucket.dto.WordCardResponseDto;
import wordsbucket.wordsbucket.model.WordCard;

@Mapper(config = MapperConfig.class)
public interface WordCardMapper {
    @Mapping(target = "wordCardId", source = "id")
    WordCardResponseDto toDto(WordCard wordCard);

    WordCard toEntity(WordCardRequestDto wordCardRequestDto);
}
