package Dohyun.Webtoon_recommender.controller;


import Dohyun.Webtoon_recommender.model.Board;
import Dohyun.Webtoon_recommender.model.Rating;
import Dohyun.Webtoon_recommender.model.WebtoonData;
import Dohyun.Webtoon_recommender.repository.BoardRepository;
import Dohyun.Webtoon_recommender.repository.RatingRepository;
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



    @GetMapping("/recommender")
    public String recommend(Model model) {
        List<WebtoonData> webtoonData = webtoonDataRepository.findAll();
        List<Rating> rating = ratingRepository.findAll();
        model.addAttribute("webtoonData", webtoonData);
        model.addAttribute("rating", new Rating());
        return "recommend/recommender";
    }

    @PostMapping("/recommender")
    public String rating(Long webtoonid, @ModelAttribute Rating rating, Authentication authentication)
    {
        String username = authentication.getName();
        ratingService.save(username, rating, webtoonid);
//        ratingService.save(username, rating);
        return "redirect:/recommend/recommender"; //redirect를 통해 다시 한번 조회가 되도록 함. 안하면 업데이트없이 창띄움.
    }


}
