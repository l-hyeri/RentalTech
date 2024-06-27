package rentaltech.electronics.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import rentaltech.electronics.dto.CartItemDto;
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
}
