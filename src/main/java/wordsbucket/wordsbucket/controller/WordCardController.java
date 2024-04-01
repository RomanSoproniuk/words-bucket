package wordsbucket.wordsbucket.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.security.Principal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
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
import wordsbucket.wordsbucket.dto.WordCardRequestDto;
import wordsbucket.wordsbucket.dto.WordCardResponseDto;
import wordsbucket.wordsbucket.dto.WordCardsBooleanIsKnowDto;
import wordsbucket.wordsbucket.dto.WordCardsBooleanIsRepeatDto;
import wordsbucket.wordsbucket.service.WordCardService;

@RestController
@RequestMapping("/word-cards")
@RequiredArgsConstructor
@Tag(name = "Word card API (controller)", description = """
        With the help of this API, you will be able to send
         requests to this controller, the description of each
          endpoint will be directly below it, this will help you
           understand how to work with this API.
        """)
public class WordCardController {
    private final WordCardService wordCardService;

    @Operation(summary = "Get all word cards for specific user", description = """ 
            With this endpoint you will be able to send requests to
             the "get all words" controller
            """)
    @GetMapping
    public List<WordCardResponseDto> getAllWordCards(Principal principal, Pageable pageable) {
        return wordCardService.getAllWordCards(principal, pageable);
    }

    @GetMapping("/get-random-word-card")
    public WordCardResponseDto getRandomWordCardFromAllCards(Principal principal) {
        return wordCardService.getRandomWordCardFromAllCards(principal);
    }

    @Operation(summary = "Save word card for current user", description = """
            You can save your personal card if you log in
            """)
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public WordCardResponseDto saveWordCard(@RequestBody WordCardRequestDto wordCardRequestDto,
                                 Principal principal) {
        return wordCardService.saveWordCard(wordCardRequestDto, principal);
    }

    @GetMapping("/repeat_words")
    public List<WordCardResponseDto> getAllWordCardsWhereRepeatTrue(Principal principal,
                                                         Pageable pageable) {
        return wordCardService.getAllWordCardsWhereRepeatTrue(principal, pageable);
    }

    @GetMapping("/know_words")
    public List<WordCardResponseDto> getAllWordCardsWhereKnowTrue(Principal principal,
                                                                  Pageable pageable) {
        return wordCardService.getAllWordCardsWhereKnowTrue(principal, pageable);
    }

    @PutMapping("/repeat_words/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateBooleanIsRepeatTrue(
            @RequestBody
            WordCardsBooleanIsRepeatDto wordCardsBooleanIsRepeatDto,
            @PathVariable Long id) {
        wordCardService.updateBooleanIsRepeatTrue(wordCardsBooleanIsRepeatDto, id);
    }

    @PutMapping("/know_words/{id}")
    public void updateBooleanIsRepeatTrue(
            @RequestBody
            WordCardsBooleanIsKnowDto wordCardsBooleanIsKnowDto,
            @PathVariable Long id) {
        wordCardService.wordCardsBooleanIsKnowDto(wordCardsBooleanIsKnowDto, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteWordCardById(@PathVariable Long id, Principal principal) {
        wordCardService.deleteWordCardById(id, principal);
    }

}
