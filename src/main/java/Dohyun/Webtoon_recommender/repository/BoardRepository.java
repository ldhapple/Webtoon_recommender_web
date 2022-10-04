package Dohyun.Webtoon_recommender.repository;

import Dohyun.Webtoon_recommender.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
