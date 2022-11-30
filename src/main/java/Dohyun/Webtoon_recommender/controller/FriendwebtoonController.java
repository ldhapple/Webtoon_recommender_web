package Dohyun.Webtoon_recommender.controller;

import Dohyun.Webtoon_recommender.model.Rating;
import Dohyun.Webtoon_recommender.model.User;
import Dohyun.Webtoon_recommender.model.WebtoonData;
import Dohyun.Webtoon_recommender.repository.RatingRepository;
import Dohyun.Webtoon_recommender.repository.UserRepository;
import Dohyun.Webtoon_recommender.repository.WebtoonDataRepository;
import Dohyun.Webtoon_recommender.service.RatingService;
import Dohyun.Webtoon_recommender.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/friend")
public class FriendwebtoonController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WebtoonDataRepository webtoonDataRepository;

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private SearchService searchService;

    @GetMapping("/friendwebtoon")
    public String friend(Model model, @RequestParam(value = "keyword", required=false) String keyword, Authentication authentication) {

        String username = authentication.getName();
//        String user_sex = userRepository.findByUsername(username).getSex();
//        if(keyword == null)
//        {
//            List<Rating> ratingList = searchService.searchRating(username);
//            model.addAttribute("rating", ratingList);
//        }else{
//            List<Rating> ratingList = searchService.searchRating(keyword);
//            model.addAttribute("rating", ratingList);
//        }

        List<Rating> ratingList = searchService.searchRating(keyword);
        model.addAttribute("rating", ratingList);

        return "friend/friendwebtoon";
    }
}
