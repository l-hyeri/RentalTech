package rentaltech.electronics.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import rentaltech.electronics.constant.ItemStockStatus;
import rentaltech.electronics.entity.Item;

@Getter
@Setter
public class ItemDto {

    @NotNull(message = "제품 시리얼 번호는 필수입니다.")
    private Long serial_num;

    private String item_name;

    private int price;

    private int stock;  // 재고 개수

    private String details; // 제품 설명

    private int period; // 렌탈 기간

    public static ItemDto toItemDto(Item item) {
        ItemDto itemDto = new ItemDto();
        itemDto.setSerial_num(item.getSerial_num());
        itemDto.setItem_name(item.getItem_name());
        itemDto.setPrice(item.getPrice());
        itemDto.setStock(item.getStock());
        item.setDetails(item.getDetails());
        item.setPeriod(item.getPeriod());

        return itemDto;
    }

}
