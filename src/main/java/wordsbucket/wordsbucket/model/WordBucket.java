package wordsbucket.wordsbucket.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "word_buckets")
@Getter
@Setter
public class WordBucket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String bucketName;
    @OneToMany
    private List<WordCard> wordCards = new ArrayList<>();
    @ManyToOne
    private User user;
}
