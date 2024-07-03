package rentaltech.electronics.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CartRentalDto {

    private Long cartItemId;
    private List<CartRentalDto> cartRentalDtoList;
}
