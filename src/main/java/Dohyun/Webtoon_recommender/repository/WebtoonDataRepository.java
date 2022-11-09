package Dohyun.Webtoon_recommender.repository;

import Dohyun.Webtoon_recommender.model.User;
import Dohyun.Webtoon_recommender.model.WebtoonData;
import org.springframework.data.jpa.repository.JpaRepository;


public interface WebtoonDataRepository extends JpaRepository<WebtoonData, Long> {
    WebtoonData findByTitleid(Long titleid);
}
