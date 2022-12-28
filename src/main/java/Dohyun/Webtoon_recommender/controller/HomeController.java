package Dohyun.Webtoon_recommender.controller;

import Dohyun.Webtoon_recommender.model.MainDemo;
import Dohyun.Webtoon_recommender.model.MbtiDemo;
import Dohyun.Webtoon_recommender.repository.MainDemoRepository;
import Dohyun.Webtoon_recommender.repository.MbtiDemoRepository;
import Dohyun.Webtoon_recommender.repository.RatingRepository;
import Dohyun.Webtoon_recommender.repository.UserRepository;
import Dohyun.Webtoon_recommender.service.RecommendService;
import org.apache.commons.collections4.MultiValuedMap;
import org.python.core.PyFunction;
import org.python.core.PyInteger;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MainDemoRepository mainDemoRepository;

    @Autowired
    private MbtiDemoRepository mbtiDemoRepository;

    @Autowired
    private RecommendService recommendService;

    @Autowired
    private RatingRepository ratingRepository;

    @GetMapping
    public String home(Model model) {
        model.addAttribute("Main", recommendService.recommend_main());

        model.addAttribute("ISTJ", recommendService.recommend_mbti("ISTJ"));
        model.addAttribute("ESFP", recommendService.recommend_mbti("ESFP"));
        model.addAttribute("ENFP", recommendService.recommend_mbti("ENFP"));
        model.addAttribute("INTJ", recommendService.recommend_mbti("INTJ"));
        model.addAttribute("INFP", recommendService.recommend_mbti("INFP"));
        model.addAttribute("ENTJ", recommendService.recommend_mbti("ENTJ"));
        model.addAttribute("ENFJ", recommendService.recommend_mbti("ENFJ"));
        model.addAttribute("ISTP", recommendService.recommend_mbti("ISTP"));
        model.addAttribute("ESFJ", recommendService.recommend_mbti("ESFJ"));
        model.addAttribute("ISFP", recommendService.recommend_mbti("ISFP"));
        model.addAttribute("ENTP", recommendService.recommend_mbti("ENTP"));
        model.addAttribute("ISFJ", recommendService.recommend_mbti("ISFJ"));
        model.addAttribute("ESTJ", recommendService.recommend_mbti("ESTJ"));
        model.addAttribute("INTP", recommendService.recommend_mbti("INTP"));
        model.addAttribute("INFJ", recommendService.recommend_mbti("INFJ"));
        model.addAttribute("ESTP", recommendService.recommend_mbti("ESTP"));

        return "home";
    }

    @GetMapping("/login")
    public String home_login(Model model, Authentication authentication) {
        String username = authentication.getName();
        String user_sex = userRepository.findByUsername(username).getSex();


        long user_id = userRepository.findByUsername(username).getId();

        if(ratingRepository.findByUserId(user_id).size() >= 5)
        {
            RestTemplate restTemplate = new RestTemplate();
            String url = "http://localhost:5000/recom_webtoon";

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);

            MultiValueMap<String, Integer> body = new LinkedMultiValueMap<>();
            body.add("user_id", (int)user_id);

            HttpEntity<?> requestMessage = new HttpEntity<>(body, httpHeaders);

            HttpEntity<String> response = restTemplate.postForEntity(url, requestMessage, String.class);
            model.addAttribute("Main", recommendService.recommend_main(user_id));

        } else {
            model.addAttribute("Main", recommendService.recommend_main_login(user_sex));
        }




        model.addAttribute("ISTJ", recommendService.recommend_mbti("ISTJ"));
        model.addAttribute("ESFP", recommendService.recommend_mbti("ESFP"));
        model.addAttribute("ENFP", recommendService.recommend_mbti("ENFP"));
        model.addAttribute("INTJ", recommendService.recommend_mbti("INTJ"));
        model.addAttribute("INFP", recommendService.recommend_mbti("INFP"));
        model.addAttribute("ENTJ", recommendService.recommend_mbti("ENTJ"));
        model.addAttribute("ENFJ", recommendService.recommend_mbti("ENFJ"));
        model.addAttribute("ISTP", recommendService.recommend_mbti("ISTP"));
        model.addAttribute("ESFJ", recommendService.recommend_mbti("ESFJ"));
        model.addAttribute("ISFP", recommendService.recommend_mbti("ISFP"));
        model.addAttribute("ENTP", recommendService.recommend_mbti("ENTP"));
        model.addAttribute("ISFJ", recommendService.recommend_mbti("ISFJ"));
        model.addAttribute("ESTJ", recommendService.recommend_mbti("ESTJ"));
        model.addAttribute("INTP", recommendService.recommend_mbti("INTP"));
        model.addAttribute("INFJ", recommendService.recommend_mbti("INFJ"));
        model.addAttribute("ESTP", recommendService.recommend_mbti("ESTP"));

        return "home_login";
    }
}
