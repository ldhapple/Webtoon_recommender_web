package Dohyun.Webtoon_recommender.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/detail")
public class DetailController {


    @GetMapping("/detailpage")
    public String detailpage(Model model) {

        return "detail/detailpage";
    }
}
