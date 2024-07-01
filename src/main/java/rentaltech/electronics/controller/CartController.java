package rentaltech.electronics.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import rentaltech.electronics.dto.CartItemDto;
import rentaltech.electronics.dto.CartListDto;
import rentaltech.electronics.service.CartService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class CartController {
    private final CartService cartService;

    // 장바구니 담기
    @PostMapping(value = "/cart")
    @ResponseBody
    public ResponseEntity cart(@RequestBody @Valid CartItemDto cartItemDto,
                               BindingResult bindingResult, HttpSession session) {

        Long cartItemId;
        String mail = (String) session.getAttribute("mail");

        if (bindingResult.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();

            for (FieldError fieldError : fieldErrors) {
                sb.append(fieldError.getDefaultMessage());
            }
            return new ResponseEntity<String>(sb.toString(), HttpStatus.BAD_REQUEST);
        }

        try {
            cartItemId = cartService.addCart(cartItemDto, mail);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Long>(cartItemId, HttpStatus.OK);
    }

    // 장바구니 목록 조회
    @GetMapping(value = "/carts")
    public String cartList(HttpSession session, Model model) {
        String mail = (String) session.getAttribute("mail");

        List<CartListDto> cartList = cartService.cartList(mail);
        model.addAttribute("cartList", cartList);
        return "carts/cartList";
    }

    // 장바구니 상품 수량 변경
    @PutMapping(value = "/carts/{cartItemId}")
    @ResponseBody
    public ResponseEntity updateCartItem(@PathVariable(name = "cartItemId") Long cartItemId, int count, HttpSession session) {
        if (count <= 0) {
            return new ResponseEntity<String>("최소 1개 이상 담아주세요.", HttpStatus.BAD_REQUEST);
        }
        cartService.updateCartItem(cartItemId, count);
        return new ResponseEntity<Long>(cartItemId, HttpStatus.OK);
    }

    // 장바구니 삭제
    @DeleteMapping(value="/carts/{cartItemId}")
    @ResponseBody
    public ResponseEntity deleteCartItem(@PathVariable(name = "cartItemId") Long cartItemId) {
        cartService.deleteCartItem(cartItemId);
        return new ResponseEntity<Long>(cartItemId, HttpStatus.OK);
    }
}
