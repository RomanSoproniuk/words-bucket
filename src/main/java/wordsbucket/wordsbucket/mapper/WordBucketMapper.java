package wordsbucket.wordsbucket.mapper;

import org.mapstruct.Mapper;
import wordsbucket.wordsbucket.config.MapperConfig;
import wordsbucket.wordsbucket.dto.WordBucketRequestDto;
import wordsbucket.wordsbucket.dto.WordBucketResponseDto;
import wordsbucket.wordsbucket.model.WordBucket;

@Mapper(config = MapperConfig.class)
public interface WordBucketMapper {
    WordBucket toEntity(WordBucketRequestDto wordBucketRequestDto);

    WordBucketResponseDto toDto(WordBucket wordBucket);
}
