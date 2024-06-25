package rentaltech.electronics.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * 회원 한 명당 1개의 장바구니를 가짐
 * -> 회원 장바구니에 해당
 * */

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Cart {

    @Id
    @Column(name = "cart_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public static Cart createCart(Member member) {  // member를 파라미터로 받아서 장바구니 생성
        Cart cart = new Cart();
        cart.setMember(member);
        return cart;
    }

}
