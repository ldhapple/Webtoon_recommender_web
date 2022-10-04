package Dohyun.Webtoon_recommender.controller;

import Dohyun.Webtoon_recommender.model.Board;
import Dohyun.Webtoon_recommender.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/board")

public class BoardController {

    @Autowired
    private BoardRepository boardRepository;

    @GetMapping("/list")
    public String list(Model model) {
        List<Board> boards = boardRepository.findAll(); //data 전부 가져옴.
        model.addAttribute("boards", boards);
        return "board/list";
    }

    @GetMapping("/form")
    public String form(Model model, @RequestParam(required = false) Long id){
        if(id == null)
        {
            model.addAttribute("board", new Board());
        }
        else {
            Board board = boardRepository.findById(id).orElse(null); // 조회된 값이 없을 수도 있어서 orElse로 null
            model.addAttribute("board",board);
        }

        return "board/form";
    }

    @PostMapping("/form")
    public String greetingSubmit(@ModelAttribute Board board)
    {
        boardRepository.save(board);
        return "redirect:/board/list"; //redirect를 통해 다시 한번 조회가 되도록 함. 안하면 업데이트없이 창띄움.
    }
}


