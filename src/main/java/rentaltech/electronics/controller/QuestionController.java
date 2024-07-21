package rentaltech.electronics.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import rentaltech.electronics.dto.QuestionDto;
import rentaltech.electronics.dto.QuestionListDto;
import rentaltech.electronics.entity.Question;
import rentaltech.electronics.service.QuestionService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping("/questions/questionForm")
    public String registerQuestion(QuestionDto questionDto, Model model) {  // 문의 사항 작성
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

    @GetMapping("/items/{itemId}/questions")
    public String questionList(@PathVariable("itemId") Long serialNum, Model model) {
        List<QuestionListDto> questionDtos = questionService.questionList(serialNum);
        model.addAttribute("questionList", questionDtos);

        return "questions/questionList";
    }

    @GetMapping("/questions/{id}")
    public String getQuestion(@PathVariable Long id,Model model) {
        Question question = questionService.questionDetail(id);
        model.addAttribute("questionDetail", question);

        return "questions/questionDetail";
    }
}
