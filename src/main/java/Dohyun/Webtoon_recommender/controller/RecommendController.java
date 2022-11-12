package Dohyun.Webtoon_recommender.controller;


import Dohyun.Webtoon_recommender.model.Board;
import Dohyun.Webtoon_recommender.model.Rating;
import Dohyun.Webtoon_recommender.model.User;
import Dohyun.Webtoon_recommender.model.WebtoonData;
import Dohyun.Webtoon_recommender.repository.BoardRepository;
import Dohyun.Webtoon_recommender.repository.RatingRepository;
import Dohyun.Webtoon_recommender.repository.UserRepository;
import Dohyun.Webtoon_recommender.repository.WebtoonDataRepository;
import Dohyun.Webtoon_recommender.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/recommend")
public class RecommendController {

    @Autowired
    private WebtoonDataRepository webtoonDataRepository;

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private RatingService ratingService;

    @Autowired
    private UserRepository userRepository;



    @GetMapping("/recommender")
    public String recommend(Model model, Authentication authentication) {
        User user = userRepository.findByUsername(authentication.getName());
        List<WebtoonData> webtoonData = webtoonDataRepository.findAll();
        List<Rating> rating = ratingRepository.findAll();
        List<Rating> userRating = ratingRepository.findByUser(user);
        model.addAttribute("webtoonData", webtoonData);
        model.addAttribute("rating", new Rating());
        model.addAttribute("userRate", userRating);
        return "recommend/recommender";
    }

    @PostMapping("/recommender")
    public String rating(Long webtoonid, @ModelAttribute Rating rating, Authentication authentication)
    {
        String username = authentication.getName();
        ratingService.save(username, rating, webtoonid);
        return "redirect:/recommend/recommender";
    }


}
