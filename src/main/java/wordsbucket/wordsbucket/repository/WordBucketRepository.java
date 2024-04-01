package wordsbucket.wordsbucket.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import wordsbucket.wordsbucket.model.WordBucket;

@Repository
public interface WordBucketRepository extends JpaRepository<WordBucket, Long> {

    @Query("FROM word_buckets w LEFT JOIN FETCH w.user WHERE w.id=:id")
    Optional<WordBucket> findById(Long id);

    @Query("FROM word_buckets w LEFT JOIN FETCH w.user u WHERE u.id=:id")
    List<WordBucket> findAllForCurrentUser(Long id);
}
