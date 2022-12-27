package Dohyun.Webtoon_recommender.repository;

import Dohyun.Webtoon_recommender.model.Contact;
import Dohyun.Webtoon_recommender.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {
}
