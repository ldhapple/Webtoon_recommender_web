package Dohyun.Webtoon_recommender.repository;

import Dohyun.Webtoon_recommender.model.Board;
import Dohyun.Webtoon_recommender.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
