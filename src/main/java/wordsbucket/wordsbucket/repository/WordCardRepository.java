package wordsbucket.wordsbucket.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import wordsbucket.wordsbucket.model.WordCard;

@Repository
public interface WordCardRepository extends JpaRepository<WordCard, Long> {
    List<WordCard> findAllByUsersEmail(String email);

    @Query("FROM word_cards w LEFT JOIN FETCH w.users u WHERE u.email=:email"
            + " AND w.isRepeat=:trueValue")
    List<WordCard> findAllByUserEmailWhereRepeatTrue(String email, boolean trueValue);

    @Query("FROM word_cards w LEFT JOIN FETCH w.users u WHERE u.email=:email "
            + "AND w.isKnow=:trueValue")
    List<WordCard> findAllByUserEmailWhereKnowTrue(String email, boolean trueValue);
}
