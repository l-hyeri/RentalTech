package rentaltech.electronics.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartListDto {

    private Long cartItemId;
    private String item_name;
    private int price;
    private int count;
    private String imgUrl;

    public CartListDto(Long cartItemId, String item_name, int price, int count, String imgUrl){
        this.cartItemId = cartItemId;
        this.item_name = item_name;
        this.price = price;
        this.count = count;
        this.imgUrl = imgUrl;
    }
}
