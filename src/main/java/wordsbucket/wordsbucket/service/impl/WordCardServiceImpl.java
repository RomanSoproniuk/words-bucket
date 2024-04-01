package wordsbucket.wordsbucket.service.impl;

import jakarta.persistence.EntityNotFoundException;
import java.security.Principal;
import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import wordsbucket.wordsbucket.dto.WordCardRequestDto;
import wordsbucket.wordsbucket.dto.WordCardResponseDto;
import wordsbucket.wordsbucket.dto.WordCardsBooleanIsKnowDto;
import wordsbucket.wordsbucket.dto.WordCardsBooleanIsRepeatDto;
import wordsbucket.wordsbucket.mapper.WordCardMapper;
import wordsbucket.wordsbucket.model.User;
import wordsbucket.wordsbucket.model.WordCard;
import wordsbucket.wordsbucket.repository.UserRepository;
import wordsbucket.wordsbucket.repository.WordCardRepository;
import wordsbucket.wordsbucket.service.WordCardService;

@Service
@RequiredArgsConstructor
public class WordCardServiceImpl implements WordCardService {
    private static final String TRUE_VALUE = "true";
    private final WordCardRepository wordCardRepository;
    private final WordCardMapper wordCardMapper;
    private final UserRepository userRepository;

    @Override
    public List<WordCardResponseDto> getAllWordCards(
            Principal principal,
            Pageable pageable) {
        return wordCardRepository.findAllByUsersEmail(principal.getName()).stream()
                .map(wordCardMapper::toDto)
                .toList();
    }

    @Override
    public WordCardResponseDto saveWordCard(WordCardRequestDto wordCardRequestDto,
                             Principal principal) {
        WordCard wordCard = wordCardMapper.toEntity(wordCardRequestDto);
        User thisUser = userRepository.findByEmail(principal.getName()).get();
        wordCard.getUsers().add(thisUser);
        return wordCardMapper.toDto(wordCardRepository.save(wordCard));
    }

    @Override
    public WordCardResponseDto getRandomWordCardFromAllCards(Principal principal) {
        List<WordCard> allWordCardFromDb = wordCardRepository
                .findAllByUsersEmail(principal.getName());
        int sizeOfWordCardsInDb = allWordCardFromDb.size();
        return wordCardMapper.toDto(allWordCardFromDb
                .get(new Random()
                        .nextInt(sizeOfWordCardsInDb)));
    }

    @Override
    public List<WordCardResponseDto> getAllWordCardsWhereRepeatTrue(
            Principal principal,
            Pageable pageable) {
        return wordCardRepository.findAllByUserEmailWhereRepeatTrue(principal
                        .getName(), true).stream()
                .map(wordCardMapper::toDto)
                .toList();
    }

    @Override
    public List<WordCardResponseDto> getAllWordCardsWhereKnowTrue(
            Principal principal, Pageable pageable) {
        return wordCardRepository.findAllByUserEmailWhereKnowTrue(principal
                        .getName(), true).stream()
                .map(wordCardMapper::toDto)
                .toList();
    }

    @Override
    public void updateBooleanIsRepeatTrue(
            WordCardsBooleanIsRepeatDto wordCardsBooleanIsRepeatDto,
            Long id) {
        WordCard wordCardFromDb = wordCardRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Word card by id: "
                + id + " doesn't exist in DB"));
        boolean booleanValueFromRequest
                = wordCardsBooleanIsRepeatDto.getIsRepeat()
                .equalsIgnoreCase(TRUE_VALUE);
        wordCardFromDb.setRepeat(booleanValueFromRequest);
        wordCardRepository.save(wordCardFromDb);
    }

    @Override
    public void wordCardsBooleanIsKnowDto(
            WordCardsBooleanIsKnowDto wordCardsBooleanIsKnowDto,
            Long id) {
        WordCard wordCardFromDb = wordCardRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Word card by id: "
                + id + " doesn't exist in DB"));
        boolean knowValue = wordCardsBooleanIsKnowDto.getIsKnow().equalsIgnoreCase(TRUE_VALUE);
        wordCardFromDb.setKnow(knowValue);
        wordCardRepository.save(wordCardFromDb);
    }

    @Override
    public void deleteWordCardById(Long id, Principal principal) {
        User currentUser = userRepository.findByEmail(principal.getName()).get();
        WordCard wordCardByIdFromDb = wordCardRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Word card by id: " + id + "doesn 't exist in DB"));
        wordCardByIdFromDb.getUsers();
    }
}
