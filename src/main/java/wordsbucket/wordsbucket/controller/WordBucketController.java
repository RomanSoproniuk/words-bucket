package wordsbucket.wordsbucket.controller;

import java.security.Principal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import wordsbucket.wordsbucket.dto.WordBucketRequestDto;
import wordsbucket.wordsbucket.dto.WordBucketResponseDto;
import wordsbucket.wordsbucket.service.WordBucketService;

@RestController
@RequestMapping("/word_buckets")
@RequiredArgsConstructor
public class WordBucketController {
    private final WordBucketService wordBucketService;

    @PostMapping
    WordBucketResponseDto createNewWordBucket(@RequestBody
                                              WordBucketRequestDto wordBucketRequestDto,
                                              Principal principal) {
        return wordBucketService.createNewWordBucket(wordBucketRequestDto, principal);
    }

    @GetMapping("/personal_word_buckets")
    List<WordBucketResponseDto> getAllWordBucketsByUser(Principal principal) {
        return wordBucketService.getAllWordBucketsByUser(principal);
    }

    @GetMapping("/popular_word_buckers")
    List<WordBucketResponseDto> getPopularWordBuckets() {
        return wordBucketService.getPopularWordBuckets();
    }

    @PutMapping("/personal_word_buckets/{id}")
    WordBucketResponseDto updateWordBucket(@RequestBody WordBucketRequestDto wordBucketRequestDto,
                                           @PathVariable Long id,
                                           Principal principal) {
        return wordBucketService.updateWordBucket(wordBucketRequestDto, id, principal);
    }

    @DeleteMapping("/personal_word_buckets/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePersonalWordBucketById(@PathVariable Long id,
                                             Principal principal) {
        wordBucketService.deletePersonalWordBucketById(id, principal);
    }
}
