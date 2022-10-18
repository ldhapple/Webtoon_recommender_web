package Dohyun.Webtoon_recommender.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/recommend")
public class RecommendController {

    @GetMapping("/recommender")
    public String recommend() {
        return "recommend/recommender";
    }

    @GetMapping("/friendwebtoon")
    public String friend() {return "recommend/friendwebtoon";}
}
