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
import rentaltech.electronics.dto.RentalDto;
import rentaltech.electronics.service.RentalService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class RentalController {

    private final RentalService rentalService;

    // 상품 상세 조회에서 바로 렌탈
    @PostMapping(value = "/rental")
    @ResponseBody
    public ResponseEntity rental(@RequestBody @Valid RentalDto rentalDto,
                                 BindingResult bindingResult, HttpSession session) {
        if (bindingResult.hasErrors()) {
            StringBuilder sb = new StringBuilder();

            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for (FieldError fieldError : fieldErrors) {
                sb.append(fieldError.getDefaultMessage());
            }

            return new ResponseEntity<String>(sb.toString(), HttpStatus.BAD_REQUEST);
        }

        Long rentalId;
        String mail = (String) session.getAttribute("mail");

        try {
            rentalId = rentalService.rental(rentalDto, mail);
            log.info(String.valueOf(rentalId));
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Long>(rentalId, HttpStatus.OK);
    }
}
