package Dohyun.Webtoon_recommender.controller;

import Dohyun.Webtoon_recommender.model.MainDemo;
import Dohyun.Webtoon_recommender.model.MbtiDemo;
import Dohyun.Webtoon_recommender.repository.MainDemoRepository;
import Dohyun.Webtoon_recommender.repository.MbtiDemoRepository;
import Dohyun.Webtoon_recommender.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    MainDemoRepository mainDemoRepository;

    @Autowired
    MbtiDemoRepository mbtiDemoRepository;

    @GetMapping
    public String home(Model model) {
        List<MainDemo> main = mainDemoRepository.findAll();
        Collections.shuffle(main);
        List<MainDemo> random_main = new ArrayList<MainDemo>(20);
        for(int i = 0; i < 20; i++)
        {
            random_main.add(main.get(i));
        }
        model.addAttribute("Main", random_main);

        List<MbtiDemo> istj = mbtiDemoRepository.findByMbti("ISTJ");
        Collections.shuffle(istj);
        List<MbtiDemo> random_istj = new ArrayList<MbtiDemo>(18);
        for(int i = 0; i < 18; i++)
        {
            random_istj.add(istj.get(i));
        }
        model.addAttribute("ISTJ", random_istj);

        List<MbtiDemo> esfp = mbtiDemoRepository.findByMbti("ESFP");
        Collections.shuffle(esfp);
        List<MbtiDemo> random_esfp = new ArrayList<MbtiDemo>(18);
        for(int i = 0; i < 18; i++)
        {
            random_esfp.add(esfp.get(i));
        }
        model.addAttribute("ESFP", random_esfp);

        List<MbtiDemo> enfp = mbtiDemoRepository.findByMbti("ENFP");
        Collections.shuffle(enfp);
        List<MbtiDemo> random_enfp = new ArrayList<MbtiDemo>(18);
        for(int i = 0; i < 18; i++)
        {
            random_enfp.add(enfp.get(i));
        }
        model.addAttribute("ENFP", random_enfp);

        List<MbtiDemo> intj = mbtiDemoRepository.findByMbti("INTJ");
        Collections.shuffle(intj);
        List<MbtiDemo> random_intj = new ArrayList<MbtiDemo>(18);
        for(int i = 0; i < 18; i++)
        {
            random_intj.add(intj.get(i));
        }
        model.addAttribute("INTJ", random_intj);

        List<MbtiDemo> infp = mbtiDemoRepository.findByMbti("INFP");
        Collections.shuffle(infp);
        List<MbtiDemo> random_infp = new ArrayList<MbtiDemo>(18);
        for(int i = 0; i < 18; i++)
        {
            random_infp.add(infp.get(i));
        }
        model.addAttribute("INFP", random_infp);

        List<MbtiDemo> entj = mbtiDemoRepository.findByMbti("ENTJ");
        Collections.shuffle(entj);
        List<MbtiDemo> random_entj = new ArrayList<MbtiDemo>(18);
        for(int i = 0; i < 18; i++)
        {
            random_entj.add(entj.get(i));
        }
        model.addAttribute("ENTJ", random_entj);

        List<MbtiDemo> enfj = mbtiDemoRepository.findByMbti("ENFJ");
        Collections.shuffle(enfj);
        List<MbtiDemo> random_enfj = new ArrayList<MbtiDemo>(18);
        for(int i = 0; i < 18; i++)
        {
            random_enfj.add(enfj.get(i));
        }
        model.addAttribute("ENFJ", random_enfj);

        List<MbtiDemo> istp = mbtiDemoRepository.findByMbti("ISTP");
        Collections.shuffle(istp);
        List<MbtiDemo> random_istp = new ArrayList<MbtiDemo>(18);
        for(int i = 0; i < 18; i++)
        {
            random_istp.add(istp.get(i));
        }
        model.addAttribute("ISTP", random_istp);

        List<MbtiDemo> esfj = mbtiDemoRepository.findByMbti("ESFJ");
        Collections.shuffle(esfj);
        List<MbtiDemo> random_esfj = new ArrayList<MbtiDemo>(18);
        for(int i = 0; i < 18; i++)
        {
            random_esfj.add(esfj.get(i));
        }
        model.addAttribute("ESFJ", random_esfj);

        List<MbtiDemo> isfp = mbtiDemoRepository.findByMbti("ISFP");
        Collections.shuffle(isfp);
        List<MbtiDemo> random_isfp = new ArrayList<MbtiDemo>(18);
        for(int i = 0; i < 18; i++)
        {
            random_isfp.add(isfp.get(i));
        }
        model.addAttribute("ISFP", random_isfp);

        List<MbtiDemo> entp = mbtiDemoRepository.findByMbti("ENTP");
        Collections.shuffle(entp);
        List<MbtiDemo> random_entp = new ArrayList<MbtiDemo>(18);
        for(int i = 0; i < 18; i++)
        {
            random_entp.add(entp.get(i));
        }
        model.addAttribute("ENTP", random_entp);

        List<MbtiDemo> isfj = mbtiDemoRepository.findByMbti("ISFJ");
        Collections.shuffle(isfj);
        List<MbtiDemo> random_isfj = new ArrayList<MbtiDemo>(18);
        for(int i = 0; i < 18; i++)
        {
            random_isfj.add(isfj.get(i));
        }
        model.addAttribute("ISFJ", random_isfj);

        List<MbtiDemo> estj = mbtiDemoRepository.findByMbti("ESTJ");
        Collections.shuffle(estj);
        List<MbtiDemo> random_estj = new ArrayList<MbtiDemo>(18);
        for(int i = 0; i < 18; i++)
        {
            random_estj.add(estj.get(i));
        }
        model.addAttribute("ESTJ", random_estj);

        List<MbtiDemo> intp = mbtiDemoRepository.findByMbti("INTP");
        Collections.shuffle(intp);
        List<MbtiDemo> random_intp = new ArrayList<MbtiDemo>(18);
        for(int i = 0; i < 18; i++)
        {
            random_intp.add(intp.get(i));
        }
        model.addAttribute("INTP", random_intp);

        List<MbtiDemo> infj = mbtiDemoRepository.findByMbti("INFJ");
        Collections.shuffle(infj);
        List<MbtiDemo> random_infj = new ArrayList<MbtiDemo>(18);
        for(int i = 0; i < 18; i++)
        {
            random_infj.add(infj.get(i));
        }
        model.addAttribute("INFJ", random_infj);

        List<MbtiDemo> estp = mbtiDemoRepository.findByMbti("ESTP");
        Collections.shuffle(estp);
        List<MbtiDemo> random_estp = new ArrayList<MbtiDemo>(18);
        for(int i = 0; i < 18; i++)
        {
            random_estp.add(estp.get(i));
        }
        model.addAttribute("ESTP", random_estp);

        return "home";
    }

    @GetMapping("/login")
    public String home_login(Model model, Authentication authentication) {
        String username = authentication.getName();
        String user_sex = userRepository.findByUsername(username).getSex();

        List<MainDemo> main_sex = mainDemoRepository.findBySex(user_sex);
        Collections.shuffle(main_sex);
        List<MainDemo> main_neu = mainDemoRepository.findBySex("Neutral");
        Collections.shuffle(main_neu);
        List<MainDemo> main = new ArrayList<MainDemo>(20);

        for(int i = 0; i < 13; i++)
        {
            main.add(main_neu.get(i));
        }

        for(int i = 0; i < 7; i++)
        {
            main.add(main_sex.get(i));
        }

        Collections.shuffle(main);

        model.addAttribute("Main", main);

        List<MbtiDemo> istj = mbtiDemoRepository.findByMbti("ISTJ");
        Collections.shuffle(istj);
        List<MbtiDemo> random_istj = new ArrayList<MbtiDemo>(18);
        for(int i = 0; i < 18; i++)
        {
            random_istj.add(istj.get(i));
        }
        model.addAttribute("ISTJ", random_istj);

        List<MbtiDemo> esfp = mbtiDemoRepository.findByMbti("ESFP");
        Collections.shuffle(esfp);
        List<MbtiDemo> random_esfp = new ArrayList<MbtiDemo>(18);
        for(int i = 0; i < 18; i++)
        {
            random_esfp.add(esfp.get(i));
        }
        model.addAttribute("ESFP", random_esfp);

        List<MbtiDemo> enfp = mbtiDemoRepository.findByMbti("ENFP");
        Collections.shuffle(enfp);
        List<MbtiDemo> random_enfp = new ArrayList<MbtiDemo>(18);
        for(int i = 0; i < 18; i++)
        {
            random_enfp.add(enfp.get(i));
        }
        model.addAttribute("ENFP", random_enfp);

        List<MbtiDemo> intj = mbtiDemoRepository.findByMbti("INTJ");
        Collections.shuffle(intj);
        List<MbtiDemo> random_intj = new ArrayList<MbtiDemo>(18);
        for(int i = 0; i < 18; i++)
        {
            random_intj.add(intj.get(i));
        }
        model.addAttribute("INTJ", random_intj);

        List<MbtiDemo> infp = mbtiDemoRepository.findByMbti("INFP");
        Collections.shuffle(infp);
        List<MbtiDemo> random_infp = new ArrayList<MbtiDemo>(18);
        for(int i = 0; i < 18; i++)
        {
            random_infp.add(infp.get(i));
        }
        model.addAttribute("INFP", random_infp);

        List<MbtiDemo> entj = mbtiDemoRepository.findByMbti("ENTJ");
        Collections.shuffle(entj);
        List<MbtiDemo> random_entj = new ArrayList<MbtiDemo>(18);
        for(int i = 0; i < 18; i++)
        {
            random_entj.add(entj.get(i));
        }
        model.addAttribute("ENTJ", random_entj);

        List<MbtiDemo> enfj = mbtiDemoRepository.findByMbti("ENFJ");
        Collections.shuffle(enfj);
        List<MbtiDemo> random_enfj = new ArrayList<MbtiDemo>(18);
        for(int i = 0; i < 18; i++)
        {
            random_enfj.add(enfj.get(i));
        }
        model.addAttribute("ENFJ", random_enfj);

        List<MbtiDemo> istp = mbtiDemoRepository.findByMbti("ISTP");
        Collections.shuffle(istp);
        List<MbtiDemo> random_istp = new ArrayList<MbtiDemo>(18);
        for(int i = 0; i < 18; i++)
        {
            random_istp.add(istp.get(i));
        }
        model.addAttribute("ISTP", random_istp);

        List<MbtiDemo> esfj = mbtiDemoRepository.findByMbti("ESFJ");
        Collections.shuffle(esfj);
        List<MbtiDemo> random_esfj = new ArrayList<MbtiDemo>(18);
        for(int i = 0; i < 18; i++)
        {
            random_esfj.add(esfj.get(i));
        }
        model.addAttribute("ESFJ", random_esfj);

        List<MbtiDemo> isfp = mbtiDemoRepository.findByMbti("ISFP");
        Collections.shuffle(isfp);
        List<MbtiDemo> random_isfp = new ArrayList<MbtiDemo>(18);
        for(int i = 0; i < 18; i++)
        {
            random_isfp.add(isfp.get(i));
        }
        model.addAttribute("ISFP", random_isfp);

        List<MbtiDemo> entp = mbtiDemoRepository.findByMbti("ENTP");
        Collections.shuffle(entp);
        List<MbtiDemo> random_entp = new ArrayList<MbtiDemo>(18);
        for(int i = 0; i < 18; i++)
        {
            random_entp.add(entp.get(i));
        }
        model.addAttribute("ENTP", random_entp);

        List<MbtiDemo> isfj = mbtiDemoRepository.findByMbti("ISFJ");
        Collections.shuffle(isfj);
        List<MbtiDemo> random_isfj = new ArrayList<MbtiDemo>(18);
        for(int i = 0; i < 18; i++)
        {
            random_isfj.add(isfj.get(i));
        }
        model.addAttribute("ISFJ", random_isfj);

        List<MbtiDemo> estj = mbtiDemoRepository.findByMbti("ESTJ");
        Collections.shuffle(estj);
        List<MbtiDemo> random_estj = new ArrayList<MbtiDemo>(18);
        for(int i = 0; i < 18; i++)
        {
            random_estj.add(estj.get(i));
        }
        model.addAttribute("ESTJ", random_estj);

        List<MbtiDemo> intp = mbtiDemoRepository.findByMbti("INTP");
        Collections.shuffle(intp);
        List<MbtiDemo> random_intp = new ArrayList<MbtiDemo>(18);
        for(int i = 0; i < 18; i++)
        {
            random_intp.add(intp.get(i));
        }
        model.addAttribute("INTP", random_intp);

        List<MbtiDemo> infj = mbtiDemoRepository.findByMbti("INFJ");
        Collections.shuffle(infj);
        List<MbtiDemo> random_infj = new ArrayList<MbtiDemo>(18);
        for(int i = 0; i < 18; i++)
        {
            random_infj.add(infj.get(i));
        }
        model.addAttribute("INFJ", random_infj);

        List<MbtiDemo> estp = mbtiDemoRepository.findByMbti("ESTP");
        Collections.shuffle(estp);
        List<MbtiDemo> random_estp = new ArrayList<MbtiDemo>(18);
        for(int i = 0; i < 18; i++)
        {
            random_estp.add(estp.get(i));
        }
        model.addAttribute("ESTP", random_estp);

        return "home_login";
    }
}
