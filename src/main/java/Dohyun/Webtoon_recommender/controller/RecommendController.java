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
import Dohyun.Webtoon_recommender.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

    @Autowired
    private SearchService searchService;



    @GetMapping("/recommender")
    public String recommend(Model model, Authentication authentication, @PageableDefault(size=40)Pageable pageable) {
        String username = authentication.getName();
        User user = userRepository.findByUsername(username);
        List<Rating> rating = ratingRepository.findAll();
        List<Rating> userRating = ratingRepository.findByUser(user);



        model.addAttribute("webtoonData", ratingService.pageList(pageable, username));
//        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
//        model.addAttribute("next", pageable.next().getPageNumber());

        model.addAttribute("rating", new Rating());
        model.addAttribute("userRate", userRating);

        return "recommend/recommender";
    }

    @GetMapping("/myrating")
    public String myrating(Model model, Authentication authentication) {
        String username = authentication.getName();
        User user = userRepository.findByUsername(username);

        List<Rating> userRating = ratingRepository.findByUser(user);
        model.addAttribute("userRate", userRating);

        List<Rating> ratingList = searchService.searchRating(username);
        model.addAttribute("myrating", ratingList);

        return "recommend/myrating";
    }

    @PostMapping("/recommender")
    public String rating(Long webtoonid, @ModelAttribute Rating rating, Authentication authentication)
    {
        String username = authentication.getName();
        ratingService.save(username, rating, webtoonid);
        return "redirect:/recommend/recommender";
    }


}
