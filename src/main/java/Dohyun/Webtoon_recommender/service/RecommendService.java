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

        //        for (Entry<User, HashMap<Item, Double>> e : data.entrySet()) {
//            for (Item j : e.getValue().keySet()) {
//                for (Item k : diff.keySet()) {
//                    double predictedValue =
//                            diff.get(k).get(j).doubleValue() + e.getValue().get(j).doubleValue();
//                    double finalValue = predictedValue * freq.get(k).get(j).intValue();
//                    uPred.put(k, uPred.get(k) + finalValue);
//                    uFreq.put(k, uFreq.get(k) + freq.get(k).get(j).intValue());
//                }
//            }
//            HashMap<Item, Double> clean = new HashMap<Item, Double>();
//            for (Item j : uPred.keySet()) {
//                if (uFreq.get(j) > 0) {
//                    clean.put(j, uPred.get(j).doubleValue() / uFreq.get(j).intValue());
//                }
//            }
//            for (Item j : InputData.items) {
//                if (e.getValue().containsKey(j)) {
//                    clean.put(j, e.getValue().get(j));
//                } else if (!clean.containsKey(j)) {
//                    clean.put(j, -1.0);
//                }
//            }
//        }
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

        for(int i = 0; i < 8; i++)
        {
            main.add(main_neu.get(i));
        }

        for(int i = 0; i < 12; i++)
        {
            main.add(main_sex.get(i));
        }

        Collections.shuffle(main);

        return main;
    }
}
