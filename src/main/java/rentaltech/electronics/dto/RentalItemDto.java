package rentaltech.electronics.dto;

import lombok.Getter;
import lombok.Setter;
import rentaltech.electronics.entity.RentalItem;

@Getter
@Setter
public class RentalItemDto {

    private String itemName;
    private int count;
    private int rentalPrice;
    private String imgUrl;

    public RentalItemDto(RentalItem rentalItem, String imgUrl) {
        this.itemName = rentalItem.getItem().getItem_name();
        this.count = rentalItem.getCount();
        this.rentalPrice = rentalItem.getRentalPrice();
        this.imgUrl = imgUrl;
    }
}
