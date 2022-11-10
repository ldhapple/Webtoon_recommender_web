package Dohyun.Webtoon_recommender.controller;

import Dohyun.Webtoon_recommender.model.Reply;
import Dohyun.Webtoon_recommender.model.User;
import Dohyun.Webtoon_recommender.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@RequiredArgsConstructor
public class ReplyController {

//    @Autowired
//    private final ReplyService replyService;
//
//    @PostMapping("/replyWrite")
//    public String replyWrite(@ModelAttribute Reply reply, Long titleId, Authentication authentication){
//        String username = authentication.getName();
//        return replyService.replyWrite(reply, username, titleId);
//    }
//
//    @PostMapping("/replyDelete")
//    public String replyDelete(@ModelAttribute Reply reply, User user){
//        return replyService.replyDelete(reply);
//    }

}
