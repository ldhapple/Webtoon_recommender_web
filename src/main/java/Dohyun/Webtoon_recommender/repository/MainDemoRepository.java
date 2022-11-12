package Dohyun.Webtoon_recommender.repository;

import Dohyun.Webtoon_recommender.model.MainDemo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MainDemoRepository extends JpaRepository<MainDemo, Long> {
    List<MainDemo> findBySex(String sex);
}
