package Dohyun.Webtoon_recommender.repository;

import Dohyun.Webtoon_recommender.model.MbtiDemo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MbtiDemoRepository extends JpaRepository<MbtiDemo, Long> {
    List<MbtiDemo> findByMbti(String mbti);
}
