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
import rentaltech.electronics.constant.Role;
import rentaltech.electronics.dto.MemberDto;
import rentaltech.electronics.entity.Item;
import rentaltech.electronics.service.ItemService;
import rentaltech.electronics.service.MemberService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final ItemService itemService;

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
        return "/";
    }

    @PostMapping("/members/login")  // session : 로그인 유지
    public String login(@ModelAttribute MemberDto memberDto, Model model, HttpSession session) {
        MemberDto loginResult = memberService.login(memberDto);

        try {
            if (loginResult != null) {
                session.setAttribute("mail", loginResult.getMail());
                Role role = loginResult.getRole();

                if (Role.ADMIN.equals(role)) {
                    return "adminHome";
                } else if (Role.USER.equals(role)) {
                    List<Item> items = itemService.findItemList();
                    model.addAttribute("items", items);
                    return "userHome";
                } else {
                    return "main";
                }

            } else {
                return "main";
            }
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "main";
        }
    }

    @GetMapping("/members/editMember")
    public String MemberEditForm(Model model, HttpSession session) {
        String mail = (String) session.getAttribute("mail");

        if (mail == null) {
            return "redirect:/";
        }

        try {
            MemberDto memberDto = memberService.findMemberByMail(mail);
            model.addAttribute("memberDto", memberDto);
            model.addAttribute("isEdit", true);
            return "members/joinForm";

        } catch (EntityNotFoundException e) {
            model.addAttribute("errorMessage", "존재하지 않는 아이디 입니다.");
            return "redirect:/";
        }
    }

    @PostMapping("/members/editMember")
    public String MemberEdit(@Valid MemberDto memberDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("isEdit", true);
            return "members/joinForm";
        }

        if (!memberDto.isPWEqual()) {
            model.addAttribute("isEdit", true);
            System.out.println("비밀번호 불일치");
            return "members/joinForm";
        }

        try {
            memberService.edit(memberDto);
            return "redirect:/";
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "members/joinForm";
        }
    }
}