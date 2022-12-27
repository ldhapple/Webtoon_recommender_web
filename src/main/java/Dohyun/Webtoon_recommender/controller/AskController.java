package Dohyun.Webtoon_recommender.controller;

import Dohyun.Webtoon_recommender.model.Contact;
import Dohyun.Webtoon_recommender.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/contact")
public class AskController {

    @Autowired
    ContactRepository contactRepository;

    @GetMapping("/ask")
    public String ask(Model model) {
        model.addAttribute("contact", new Contact());
        return "contact/ask";
    }

    @PostMapping("/ask")
    public String contact(@ModelAttribute Contact contact)
    {
        contactRepository.save(contact);
        return "redirect:/contact/ask";
    }
}
