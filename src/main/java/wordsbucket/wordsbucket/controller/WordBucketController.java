package wordsbucket.wordsbucket.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Word bucket API (controller)", description = """
        With the help of this API, you will be able to send
         requests to this controller, the description of each
          endpoint will be directly below it, this will help you
           understand how to work with this API.
        """)
public class WordBucketController {
    private final WordBucketService wordBucketService;

    @Operation(summary = "Create new word bucket", description = """ 
            User can create personal word bucket
            """)
    @PostMapping
    WordBucketResponseDto createNewWordBucket(@RequestBody
                                              WordBucketRequestDto wordBucketRequestDto,
                                              Principal principal) {
        return wordBucketService.createNewWordBucket(wordBucketRequestDto, principal);
    }

    @Operation(summary = "Get all word buckets for user", description = """ 
            User can see all personal buckets
            """)
    @GetMapping("/personal_word_buckets")
    List<WordBucketResponseDto> getAllWordBucketsByUser(Principal principal) {
        return wordBucketService.getAllWordBucketsByUser(principal);
    }

    @Operation(summary = "Get all popular bucket", description = """ 
            User can see all popular bucket created by default for all
            """)
    @GetMapping("/popular_word_buckers")
    List<WordBucketResponseDto> getPopularWordBuckets() {
        return wordBucketService.getPopularWordBuckets();
    }

    @Operation(summary = "Update personal word bucket", description = """ 
            User can update personal word bucket
            """)
    @PutMapping("/personal_word_buckets/{id}")
    WordBucketResponseDto updateWordBucket(@RequestBody WordBucketRequestDto wordBucketRequestDto,
                                           @PathVariable Long id,
                                           Principal principal) {
        return wordBucketService.updateWordBucket(wordBucketRequestDto, id, principal);
    }

    @Operation(summary = "Delete personal word bucket", description = """ 
            User can delete by id word bucket if he is owner of this bucket
            """)
    @DeleteMapping("/personal_word_buckets/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePersonalWordBucketById(@PathVariable Long id,
                                             Principal principal) {
        wordBucketService.deletePersonalWordBucketById(id, principal);
    }
}
