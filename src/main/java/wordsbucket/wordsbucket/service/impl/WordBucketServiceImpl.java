package wordsbucket.wordsbucket.service.impl;

import jakarta.persistence.EntityNotFoundException;
import java.security.Principal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import wordsbucket.wordsbucket.dto.WordBucketRequestDto;
import wordsbucket.wordsbucket.dto.WordBucketResponseDto;
import wordsbucket.wordsbucket.exception.EnoughRightException;
import wordsbucket.wordsbucket.mapper.WordBucketMapper;
import wordsbucket.wordsbucket.model.User;
import wordsbucket.wordsbucket.model.WordBucket;
import wordsbucket.wordsbucket.repository.UserRepository;
import wordsbucket.wordsbucket.repository.WordBucketRepository;
import wordsbucket.wordsbucket.service.WordBucketService;

@Service
@RequiredArgsConstructor
public class WordBucketServiceImpl implements WordBucketService {
    private static final Long DEFAULT_ID_USER_FOR_POPULAR_WORD_BUCKETS = 1L;
    private final WordBucketRepository wordBucketRepository;
    private final WordBucketMapper wordBucketMapper;
    private final UserRepository userRepository;

    @Override
    public WordBucketResponseDto createNewWordBucket(
            WordBucketRequestDto wordBucketRequestDto,
            Principal principal) {
        User currentUser = userRepository.findByEmail(principal.getName()).get();
        WordBucket newWordBucket = wordBucketMapper.toEntity(wordBucketRequestDto);
        newWordBucket.setUser(currentUser);
        return wordBucketMapper.toDto(wordBucketRepository.save(newWordBucket));
    }

    @Override
    public void deletePersonalWordBucketById(Long id, Principal principal) {
        User currentUser = userRepository.findByEmail(principal.getName()).get();
        WordBucket wordBucketFromDb = wordBucketRepository.findById(id).orElseThrow(()
                -> new EntityNotFoundException("Word bucket"
                + " by id: " + id + " doen't exist in DB"));
        Long currentUserId = currentUser.getId();
        User userFromWordBucketDb = wordBucketFromDb.getUser();
        if (userFromWordBucketDb.getId().equals(currentUserId)) {
            wordBucketRepository.delete(wordBucketFromDb);
            return;
        }
        throw new EnoughRightException("You dont have enough right to delete this word bucket");
    }

    @Override
    public WordBucketResponseDto updateWordBucket(
            WordBucketRequestDto wordBucketRequestDto,
            Long id,
            Principal principal) {
        User currentUser = userRepository.findByEmail(principal.getName()).get();
        WordBucket wordBucketFromDb = wordBucketRepository.findById(id).orElseThrow(()
                -> new EntityNotFoundException("Word bucket"
                + " by id: " + id + " doen't exist in DB"));
        Long currentUserId = currentUser.getId();
        User userFromWordBucketDb = wordBucketFromDb.getUser();
        if (userFromWordBucketDb.getId().equals(currentUserId)) {
            wordBucketFromDb.setBucketName(wordBucketRequestDto.getBucketName());
            return wordBucketMapper.toDto(wordBucketRepository.save(wordBucketFromDb));
        }
        throw new EnoughRightException("You dont have enough right to update this word bucket");
    }

    @Override
    public List<WordBucketResponseDto> getAllWordBucketsByUser(Principal principal) {
        User currentUser = userRepository.findByEmail(principal.getName()).get();
        return wordBucketRepository.findAllForCurrentUser(currentUser.getId()).stream()
                .map(wordBucketMapper::toDto)
                .toList();
    }

    @Override
    public List<WordBucketResponseDto> getPopularWordBuckets() {
        return wordBucketRepository
                .findAllForCurrentUser(DEFAULT_ID_USER_FOR_POPULAR_WORD_BUCKETS).stream()
                .map(wordBucketMapper::toDto)
                .toList();
    }
}
