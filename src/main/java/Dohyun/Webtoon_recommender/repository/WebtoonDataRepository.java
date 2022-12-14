package Dohyun.Webtoon_recommender.repository;

import Dohyun.Webtoon_recommender.model.User;
import Dohyun.Webtoon_recommender.model.WebtoonData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface WebtoonDataRepository extends JpaRepository<WebtoonData, Long> {
    WebtoonData findByTitleid(Long titleid);
    List<WebtoonData> findByTitlenameContaining(String titlename);
}
