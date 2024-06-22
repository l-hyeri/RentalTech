package rentaltech.electronics.controller;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import rentaltech.electronics.dto.ItemDto;
import rentaltech.electronics.entity.Item;
import rentaltech.electronics.service.ItemService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/items/register")
    public String itemRegisterForm(ItemDto itemDto, Model model) {  // 상품 등록
        model.addAttribute("itemRegisterForm", itemDto);
        model.addAttribute("isEdit", false);
        return "items/itemRegisterForm";
    }

    @PostMapping("/items/register")
    public String itemRegister(@Valid ItemDto itemDto, Model model, BindingResult bindingResult,
                               @RequestParam(name = "itemImgFile") List<MultipartFile> itemImgFileList) {

        if (bindingResult.hasErrors()) {
            return "items/itemRegisterForm";
        }

        try {
            itemService.save(itemDto, itemImgFileList);
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "items/itemRegisterForm";
        }
        return "adminHome";
    }

    @GetMapping("/items/list")
    public String list(Model model) {   // 전체 상품 조회
        List<Item> items = itemService.findItemList();

        model.addAttribute("items", items);
        return "items/itemList";
    }

    @GetMapping("/items/edit/{serialNum}")
    public String ItemEditForm(@PathVariable("serialNum") Long serialNum, Model model) {
        try {
            ItemDto itemDto = itemService.findItemDetails(serialNum);
            model.addAttribute("itemDto", itemDto);
            model.addAttribute("isEdit", true);
            return "items/itemRegisterForm";

        } catch (EntityNotFoundException e) {
            model.addAttribute("errorMessage", "존재하지 않는 상품입니다.");
            return "items/itemList";
        }
    }

    @PostMapping("/items/edit/{serialNum}")
    public String ItemEdit(@PathVariable("serialNum") Long serialNum, @Valid ItemDto itemDto, BindingResult result, Model model,
                           @RequestParam(name = "itemImgFile") List<MultipartFile> itemImgFileList) {
        if (result.hasErrors()) {
            model.addAttribute("isEdit", true);

            List<Item> items = itemService.findItemList();
            model.addAttribute("items", items);
            return "items/itemList";
        }

        try {
            itemService.editItem(itemDto, serialNum, itemImgFileList);

            List<Item> items = itemService.findItemList();
            model.addAttribute("items", items);
            return "items/itemList";
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());

            List<Item> items = itemService.findItemList();
            model.addAttribute("items", items);
            return "items/itemList";
        }
    }

    // 상품 상세 페이지
    @GetMapping("/items/{itemId}")
    public String itemDetail(Model model, @PathVariable("itemId") Long serialNum) {
        ItemDto itemDto = itemService.findItemDetails(serialNum);
        model.addAttribute("item", itemDto);
        return "items/itemDetail";
    }
}
