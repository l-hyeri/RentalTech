package rentaltech.electronics.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import rentaltech.electronics.dto.QuestionDto;
import rentaltech.electronics.service.QuestionService;

@Controller
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping("/questions/questionForm")
    public String registerQuestion(QuestionDto questionDto, Model model) {
        model.addAttribute("questionForm", questionDto);
        return "questions/questionForm";
    }

    @PostMapping("/questions/questionForm")
    public String register(@Valid QuestionDto questionDto, HttpSession session, Model model, BindingResult result) {

        if (result.hasErrors()) {
            return "questions/questionForm";
        }

        try {
            String mail = (String) session.getAttribute("mail");
            questionService.createQuestion(questionDto, mail);
            
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/items/" + questionDto.getSerialNum();
    }
}
