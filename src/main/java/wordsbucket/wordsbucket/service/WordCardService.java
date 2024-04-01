package wordsbucket.wordsbucket.service;

import java.security.Principal;
import java.util.List;
import org.springframework.data.domain.Pageable;
import wordsbucket.wordsbucket.dto.WordCardRequestDto;
import wordsbucket.wordsbucket.dto.WordCardResponseDto;
import wordsbucket.wordsbucket.dto.WordCardsBooleanIsKnowDto;
import wordsbucket.wordsbucket.dto.WordCardsBooleanIsRepeatDto;

public interface WordCardService {
    List<WordCardResponseDto> getAllWordCards(Principal principal,
                                              Pageable pageable);

    WordCardResponseDto saveWordCard(WordCardRequestDto wordCardRequestDto,
                                     Principal principal);

    WordCardResponseDto getRandomWordCardFromAllCards(Principal principal);

    List<WordCardResponseDto> getAllWordCardsWhereRepeatTrue(
            Principal principal,
             Pageable pageable);

    List<WordCardResponseDto> getAllWordCardsWhereKnowTrue(
            Principal principal,
            Pageable pageable);

    void updateBooleanIsRepeatTrue(
            WordCardsBooleanIsRepeatDto wordCardsBooleanIsRepeatDto,
            Long id);

    void wordCardsBooleanIsKnowDto(
            WordCardsBooleanIsKnowDto wordCardsBooleanIsKnowDto,
            Long id);

    void deleteWordCardById(Long id, Principal principal);
}
