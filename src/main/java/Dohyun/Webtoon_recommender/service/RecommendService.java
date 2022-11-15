package Dohyun.Webtoon_recommender.service;

import Dohyun.Webtoon_recommender.model.MainDemo;
import Dohyun.Webtoon_recommender.model.MbtiDemo;
import Dohyun.Webtoon_recommender.repository.MainDemoRepository;
import Dohyun.Webtoon_recommender.repository.MbtiDemoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class RecommendService {
    @Autowired
    private MainDemoRepository mainDemoRepository;

    @Autowired
    private MbtiDemoRepository mbtiDemoRepository;

    public List<MainDemo> recommend_main(){
        List<MainDemo> main = mainDemoRepository.findAll();
        Collections.shuffle(main);
        List<MainDemo> random_main = new ArrayList<MainDemo>(20);
        for(int i = 0; i < 20; i++)
        {
            random_main.add(main.get(i));
        }

        return random_main;
    }

    public List<MbtiDemo> recommend_mbti(String mbti){
        List<MbtiDemo> mbti_recommend = mbtiDemoRepository.findByMbti(mbti);
        Collections.shuffle(mbti_recommend);
        List<MbtiDemo> random_mbti_recommend = new ArrayList<MbtiDemo>(18);
        for(int i = 0; i < 18; i++)
        {
            random_mbti_recommend.add(mbti_recommend.get(i));
        }

        return random_mbti_recommend;
    }

    public List<MainDemo> recommend_main_login(String user_sex){
        List<MainDemo> main_sex = mainDemoRepository.findBySex(user_sex);
        Collections.shuffle(main_sex);
        List<MainDemo> main_neu = mainDemoRepository.findBySex("Neutral");
        Collections.shuffle(main_neu);
        List<MainDemo> main = new ArrayList<MainDemo>(20);

        for(int i = 0; i < 9; i++)
        {
            main.add(main_neu.get(i));
        }

        for(int i = 0; i < 11; i++)
        {
            main.add(main_sex.get(i));
        }

        Collections.shuffle(main);

        return main;
    }
}
