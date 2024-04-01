package wordsbucket.wordsbucket.dto;

import lombok.Getter;
import lombok.Setter;
import wordsbucket.wordsbucket.model.Note;

@Getter
@Setter
public class WordCardResponseDto {
    private Long wordCardId;
    private String frontSideCard;
    private String backSideCard;
    private Note note;
}
