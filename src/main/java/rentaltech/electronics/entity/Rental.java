package rentaltech.electronics.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import rentaltech.electronics.constant.RentalStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "rental_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private LocalDateTime rentalDate;   // 렌탈 신청 날짜

    @Enumerated(EnumType.STRING)
    private RentalStatus rentalStatus;    // 주문 상태

    // rental_item 테이블의 rental 필드에 매핑
    @OneToMany(mappedBy = "rentals", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<RentalItem> rentalItemList = new ArrayList<>();

    public void addRentalItem(RentalItem rentalItem) {
        rentalItemList.add(rentalItem);
        rentalItem.setRentals(this);
    }

    public static Rental createRental(Member member, List<RentalItem> rentalItemList) {

        Rental rental = new Rental();
        rental.setMember(member);
        for (RentalItem rentalItem : rentalItemList) {
            rental.addRentalItem(rentalItem);
        }
        rental.setRentalDate(LocalDateTime.now());
        rental.setRentalStatus(RentalStatus.RENTAL);
        return rental;
    }

    public int getTotalPrice() {
        int totalPrice = 0;

        for (RentalItem rentalItem : rentalItemList) {
            totalPrice += rentalItem.getTotalPrice();
        }
        return totalPrice;
    }

    // 렌탈 취소
    public void rentalCancel() {
        this.rentalStatus = RentalStatus.CANCEL;
        for (RentalItem rentalItem : rentalItemList) {
            rentalItem.cancel();
        }
    }
}
