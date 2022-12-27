package Dohyun.Webtoon_recommender.repository;

import Dohyun.Webtoon_recommender.model.Recommendation;
import Dohyun.Webtoon_recommender.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecommendationRepository extends JpaRepository<Recommendation, Long> {
    List<Recommendation> findByUserId(Long user_id);

}
