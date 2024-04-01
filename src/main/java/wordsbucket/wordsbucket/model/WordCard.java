package wordsbucket.wordsbucket.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity(name = "word_cards")
@Getter
@Setter
public class WordCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String frontSideCard;
    @Column(nullable = false)
    private String backSideCard;
    @OneToOne
    @Cascade(value = CascadeType.ALL)
    private Note note;
    private boolean isRepeat = false;
    private boolean isKnow = false;
    @ManyToMany
    private List<User> users = new ArrayList<>();
    @Column(name = "is_deleted")
    private boolean isDeleted;
}
