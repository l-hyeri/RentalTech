package rentaltech.electronics.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;
import rentaltech.electronics.constant.ItemStockStatus;

@Getter
@Setter
public class MainItemDto {

    private Long serialNum;
    private String item_name;
    private int price;
    private String imgUrl;
    private ItemStockStatus stockStatus;    // 재고 상태 (판매중, 품절)

    @QueryProjection
    public MainItemDto(Long serialNum, String item_name, int price, String imgUrl,ItemStockStatus stockStatus) {
        this.serialNum = serialNum;
        this.item_name = item_name;
        this.price = price;
        this.imgUrl = imgUrl;
        this.stockStatus = stockStatus;
    }

}
