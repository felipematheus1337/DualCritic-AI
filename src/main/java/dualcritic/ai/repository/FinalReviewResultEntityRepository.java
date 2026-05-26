package dualcritic.ai.repository;

import dualcritic.ai.entity.FinalReviewResultEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinalReviewResultEntityRepository extends JpaRepository<FinalReviewResultEntity, Long> {
}
