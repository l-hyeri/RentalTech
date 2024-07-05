package rentaltech.electronics.dto;

import lombok.Getter;
import lombok.Setter;
import rentaltech.electronics.entity.RentalItem;

@Getter
@Setter
public class RentalItemDto {

    private String item_name;
    private int count;
    private int rentalPrice;
    private String imgURl;

    public RentalItemDto(RentalItem rentalItem, String imgURl) {
        this.item_name = rentalItem.getItem().getItem_name();
        this.count = rentalItem.getCount();
        this.rentalPrice = rentalItem.getRentalPrice();
        this.imgURl = imgURl;
    }
}
