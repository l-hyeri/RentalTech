package rentaltech.electronics.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import rentaltech.electronics.constant.ItemStockStatus;
import rentaltech.electronics.dto.ItemDto;
import rentaltech.electronics.exception.OutOfStockException;

@Getter
@Setter
@Entity
public class Item { // 카테고리(categories-List)

    @Id
    private Long serialNum;

    private String item_name;

    private int price;

    @Enumerated(EnumType.STRING)
    private ItemStockStatus stockStatus;    // 재고 상태

    private int stock;  // 재고 개수

    private String details; // 제품 설명

    private int period; // 렌탈 기간

    public static Item toItem(ItemDto itemDto) {
        Item item = new Item();
        item.setSerialNum(itemDto.getSerialNum());
        item.setItem_name(itemDto.getItem_name());
        item.setPrice(itemDto.getPrice());
        item.setStockStatus(itemDto.getStockStatus());
        item.setStock(itemDto.getStock());
        item.setDetails(itemDto.getDetails());
        item.setPeriod(itemDto.getPeriod());

        return item;
    }

    public void removeStock(int stock) {
        int remainStock = this.stock - stock;

        if (remainStock < 0) {
            throw new OutOfStockException("상품 재고 부족");
        }
        this.stock = remainStock;
    }

    public void addStock(int stock) {
        this.stock += stock;
    }
}
