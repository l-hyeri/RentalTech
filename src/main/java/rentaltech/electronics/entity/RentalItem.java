package rentaltech.electronics.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class RentalItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "rental_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rental_id")
    private Rental rental;

    private int rentalPrice; // 주문가격
    private int count;  // 주문 수량

    public static RentalItem createRentalItem(Item item, int count) {

        RentalItem rentalItem = new RentalItem();
        rentalItem.setItem(item);
        rentalItem.setCount(count);
        rentalItem.setRentalPrice(item.getPrice());

        item.removeStock(count);
        return rentalItem;
    }

    public int getTotalPrice() {
        return this.rentalPrice * this.count;
    }
}
