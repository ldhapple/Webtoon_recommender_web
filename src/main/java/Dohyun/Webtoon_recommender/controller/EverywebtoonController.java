package Dohyun.Webtoon_recommender.controller;

import Dohyun.Webtoon_recommender.model.Rating;
import Dohyun.Webtoon_recommender.model.WebtoonData;
import Dohyun.Webtoon_recommender.repository.WebtoonDataRepository;
import Dohyun.Webtoon_recommender.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/searchwebtoon")
public class EverywebtoonController {

    @Autowired
    private WebtoonDataRepository webtoonDataRepository;

    @Autowired
    private SearchService searchService;

    @GetMapping("/everywebtoon")
    public String friend(Model model, @RequestParam(value = "keyword", required=false) String keyword) {

        if(keyword == null)
        {
            List<WebtoonData> webtoonList = webtoonDataRepository.findAll();
            model.addAttribute("webtoon", webtoonList);
        }else{
            List<WebtoonData> searchWebtoon = searchService.searchWebtoon(keyword);
            model.addAttribute("webtoon", searchWebtoon);
        }

        return "searchwebtoon/everywebtoon";
    }
}
