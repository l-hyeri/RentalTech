package rentaltech.electronics.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import rentaltech.electronics.dto.joinFormDto;
import rentaltech.electronics.entity.Member;
import rentaltech.electronics.service.MemberService;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;


    @GetMapping("/members/join")
    public String joinForm(joinFormDto joinForm, Model model) {
        model.addAttribute("joinForm", joinForm);
        return "members/joinForm";
    }

    @PostMapping("/members/join")
    public String join(@Valid joinFormDto form, BindingResult result) {
        if (result.hasErrors()) {
            return "members/joinForm";
        }

        Member member = new Member();
        member.setId(form.getMember_id());
        member.setPw(form.getPw());
        member.setName(form.getName());
        member.setPhone(form.getPhone());
        member.setAddress(form.getAddress());
        member.setMail(form.getMail());

        memberService.join(member);

        return "redirect:/";
    }
}
