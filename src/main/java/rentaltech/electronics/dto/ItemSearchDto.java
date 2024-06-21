package rentaltech.electronics.dto;

import lombok.Getter;
import lombok.Setter;
import rentaltech.electronics.constant.ItemStockStatus;

@Getter
@Setter
public class ItemSearchDto {

    private String searchDateType;
    private ItemStockStatus searchSellStatus;
    private String searchBy;
    private String searchQuery = "";
}
