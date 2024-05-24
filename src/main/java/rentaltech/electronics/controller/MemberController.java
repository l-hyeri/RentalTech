package rentaltech.electronics.controller;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import rentaltech.electronics.dto.MemberDto;
import rentaltech.electronics.service.MemberService;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members/join")
    public String joinForm(MemberDto memberDto, Model model) {
        model.addAttribute("joinForm", memberDto);
        model.addAttribute("isEdit", false);
        return "members/joinForm";
    }

    @PostMapping("/members/join")
    public String join(@Valid MemberDto form, BindingResult result, Model model) {
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

    @GetMapping("/members/login")
    public String loginForm(MemberDto memberDto, Model model) {
        model.addAttribute("loginForm", memberDto);
        return "members/loginForm";
    }

    @PostMapping("/members/login")  // session : 로그인 유지
    public String login(@ModelAttribute MemberDto memberDto, Model model, HttpSession session) {
        MemberDto loginResult = memberService.login(memberDto);

        try {
            if (loginResult != null) {
                session.setAttribute("mail", loginResult.getMail());
            } else {
                return "members/loginForm";
            }
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "members/loginForm";
        }
        return "adminHome";
    }

    @GetMapping("/members/editMember")
    public String showForm(Model model, HttpSession session,MemberDto memberDto) {
        String mail = (String) session.getAttribute("mail");

        if (mail == null) {
            System.out.println("사용자 정보가 세션에 없습니다.");
            return "redirect:/members/login";
        }

        try {
            System.out.println("세션에 저장된 사용자: " + mail);
            //MemberDto memberDto = memberService.findByMail(mail);
            model.addAttribute("joinForm", memberDto);

            // 회원가입 폼을 동일하게 사용하기에 회원가입인지 회원정보 수정인지 확인하기 위함.
            model.addAttribute("isEdit", true);
            return "members/joinForm";

        } catch (EntityNotFoundException e) {
            model.addAttribute("errorMessage", "존재하지 않는 아이디 입니다.");
            return "redirect:/members/login";
        }
    }
}