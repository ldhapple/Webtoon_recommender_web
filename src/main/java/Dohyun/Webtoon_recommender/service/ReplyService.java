package Dohyun.Webtoon_recommender.service;

import Dohyun.Webtoon_recommender.model.Rating;
import Dohyun.Webtoon_recommender.model.Reply;
import Dohyun.Webtoon_recommender.model.User;
import Dohyun.Webtoon_recommender.model.WebtoonData;
import Dohyun.Webtoon_recommender.repository.ReplyRepository;
import Dohyun.Webtoon_recommender.repository.UserRepository;
import Dohyun.Webtoon_recommender.repository.WebtoonDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReplyService {

    @Autowired
    private final ReplyRepository replyRepository;

    @Autowired
    private final WebtoonDataRepository webtoonDataRepository;

    @Autowired
    private final UserRepository userRepository;

    public Reply replyWrite(Reply reply, String username, Long titleId){
        User findUser = userRepository.findByUsername(username);
        Optional<WebtoonData> findWebtoon = webtoonDataRepository.findById(titleId);

        reply.setWebtoon(findWebtoon.get());
        reply.setUser(findUser);

        return replyRepository.save(reply);
    }

    public String replyDelete(Reply reply){
        replyRepository.delete(reply);

        return "redirect:/detail/detailpage";
    }

//    public Reply save(String username, Reply reply, Long title_id){
//        User user = userRepository.findByUsername(username);
//        WebtoonData webtoon = webtoonDataRepository.findByTitleid(title_id);
//
//        reply.setUser(user);
//        reply.setWebtoon(webtoon);
//        return replyRepository.save(reply);
//    }

}
