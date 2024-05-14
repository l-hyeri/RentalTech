package rentaltech.electronics.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import rentaltech.electronics.dto.memberDto;
import rentaltech.electronics.service.MemberService;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;


    @GetMapping("/members/join")
    public String joinForm(memberDto memberDto, Model model) {
        model.addAttribute("joinForm", memberDto);
        return "members/joinForm";
    }

    @PostMapping("/members/join")
    public String join(@Valid memberDto form, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "members/joinForm";
        }

        if (!form.isPWEqual()) {
            System.out.println("비밀번호 불일치");
            return "members/joinForm";
        }

        try {
            memberService.join(form);
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "members/joinForm";
        }
        return "redirect:/";
    }
}
