package rentaltech.electronics.dto;

import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemDto {

    private Long serialNum;

    @Min(value = 1, message = "최소 1개 이상 담아주세요.")
    private int count;
}