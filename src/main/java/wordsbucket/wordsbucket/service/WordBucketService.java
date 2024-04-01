package wordsbucket.wordsbucket.service;

import java.security.Principal;
import java.util.List;
import wordsbucket.wordsbucket.dto.WordBucketRequestDto;
import wordsbucket.wordsbucket.dto.WordBucketResponseDto;

public interface WordBucketService {
    WordBucketResponseDto createNewWordBucket(
            WordBucketRequestDto wordBucketRequestDto,
            Principal principal);

    void deletePersonalWordBucketById(Long id, Principal principal);

    WordBucketResponseDto updateWordBucket(
            WordBucketRequestDto wordBucketRequestDto,
            Long id,
            Principal principal);

    List<WordBucketResponseDto> getAllWordBucketsByUser(Principal principal);

    List<WordBucketResponseDto> getPopularWordBuckets();
}
