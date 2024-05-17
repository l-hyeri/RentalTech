package rentaltech.electronics.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import rentaltech.electronics.dto.ItemDto;
import rentaltech.electronics.service.ItemService;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/items/register")
    public String itemRegisterForm(ItemDto itemDto, Model model) {  // 상품 등록
        model.addAttribute("itemRegisterForm", itemDto);
        return "items/itemRegisterForm";
    }

    @PostMapping("/items/register")
    public String itemRegister(@Valid ItemDto itemDto, Model model) {
        try {
            itemService.save(itemDto);
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "items/itemRegisterForm";
        }
        return "adminHome";
    }
}
