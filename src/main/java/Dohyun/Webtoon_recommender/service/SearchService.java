package Dohyun.Webtoon_recommender.service;

import Dohyun.Webtoon_recommender.model.Rating;
import Dohyun.Webtoon_recommender.model.User;
import Dohyun.Webtoon_recommender.model.WebtoonData;
import Dohyun.Webtoon_recommender.repository.RatingRepository;
import Dohyun.Webtoon_recommender.repository.UserRepository;
import Dohyun.Webtoon_recommender.repository.WebtoonDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SearchService {
    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WebtoonDataRepository webtoonDataRepository;

    @Transactional
    public List<Rating> searchRating(String keyword){
        User user = userRepository.findByUsername(keyword);
        List<Rating> rating = ratingRepository.findByUser(user);

        return rating;
    }
}
