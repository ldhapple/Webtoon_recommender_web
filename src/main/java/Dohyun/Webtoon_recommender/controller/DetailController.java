package Dohyun.Webtoon_recommender.controller;

import Dohyun.Webtoon_recommender.model.Reply;
import Dohyun.Webtoon_recommender.model.User;
import Dohyun.Webtoon_recommender.model.WebtoonData;
import Dohyun.Webtoon_recommender.repository.ReplyRepository;
import Dohyun.Webtoon_recommender.repository.WebtoonDataRepository;
import Dohyun.Webtoon_recommender.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/detail")
public class DetailController {

    @Autowired
    WebtoonDataRepository webtoonDataRepository;

    @Autowired
    private ReplyService replyService;

    @Autowired
    private ReplyRepository replyRepository;

    @GetMapping("/detailpage")
    public String detailpage(Model model, @RequestParam("id") Long titleid) {
        WebtoonData webtoonData = webtoonDataRepository.findByTitleid(titleid);
        List<Reply> reply = replyRepository.findByWebtoon(webtoonData);
        model.addAttribute("webtoonData", webtoonData);
        model.addAttribute("reply", reply);
        return "detail/detailpage";
    }

    @PostMapping("/detailpage/reply_write")
    public String replyWrite(@ModelAttribute Reply reply, Long titleId, Authentication authentication){
        String username = authentication.getName();
        replyService.replyWrite(reply, username, titleId);
        return "redirect:/detail/detailpage/?id=" + titleId;
    }

    @PostMapping("/detailpage/reply_delete")
    public String replyDelete(@ModelAttribute Reply reply, User user){
        return replyService.replyDelete(reply);
    }
}
