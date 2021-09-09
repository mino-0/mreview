package org.mino.mreview.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.mino.mreview.dto.MovieDTO;
import org.mino.mreview.service.MovieService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/movie")
@Log4j2
public class MovieController {
    private final MovieService movieService;
    @GetMapping("/register")
    public void register() {
    }

    @PostMapping("/register")
    public String register(MovieDTO movieDTO, RedirectAttributes rttr) {
        log.info("movieDTO: " + movieDTO);

        Long mno = movieService.register(movieDTO);

        rttr.addFlashAttribute("msg", mno);
        return "redirect:/movie/list";
    }
}
