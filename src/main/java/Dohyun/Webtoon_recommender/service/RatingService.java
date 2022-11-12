package Dohyun.Webtoon_recommender.service;

import Dohyun.Webtoon_recommender.model.Rating;
import Dohyun.Webtoon_recommender.model.User;
import Dohyun.Webtoon_recommender.model.WebtoonData;
import Dohyun.Webtoon_recommender.repository.RatingRepository;
import Dohyun.Webtoon_recommender.repository.UserRepository;
import Dohyun.Webtoon_recommender.repository.WebtoonDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.constraints.Null;
import java.util.List;
import java.util.Optional;

@Service
public class RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WebtoonDataRepository webtoonDataRepository;

    public Rating save(String username, Rating rating, Long title_id){
        User user = userRepository.findByUsername(username);
        WebtoonData webtoon = webtoonDataRepository.findByTitleid(title_id);

        rating.setUser(user);
        rating.setWebtoon(webtoon);
        return ratingRepository.save(rating);
    }

    public Rating save(String username, Rating rating){
        User user = userRepository.findByUsername(username);
        rating.setUser(user);
        return ratingRepository.save(rating);
    }

    public Page<WebtoonData> pageList(Pageable pageable){
        return webtoonDataRepository.findAll(pageable);
    }

//    @Transactional
//    public Page<Rating> search(String username, Pageable pageable){
//        Page<Rating> ratingList = ratingRepository.findByUsername(username, pageable);
//        return ratingList;
//    }
}
