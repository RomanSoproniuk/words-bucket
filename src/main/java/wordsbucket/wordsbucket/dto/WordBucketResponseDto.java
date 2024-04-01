package wordsbucket.wordsbucket.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import wordsbucket.wordsbucket.model.WordCard;

@Getter
@Setter
public class WordBucketResponseDto {
    private Long id;
    private String bucketName;
    private List<WordCard> wordCard = new ArrayList<>();
}
