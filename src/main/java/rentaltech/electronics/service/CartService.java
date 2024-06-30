package rentaltech.electronics.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import rentaltech.electronics.dto.CartItemDto;
import rentaltech.electronics.dto.CartListDto;
import rentaltech.electronics.entity.Cart;
import rentaltech.electronics.entity.CartItem;
import rentaltech.electronics.entity.Item;
import rentaltech.electronics.entity.Member;
import rentaltech.electronics.repository.CartItemRepository;
import rentaltech.electronics.repository.CartRepository;
import rentaltech.electronics.repository.ItemRepository;
import rentaltech.electronics.repository.MemberRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    // 장바구니 담기
    public Long addCart(CartItemDto cartItemDto, String email) {

        Optional<Member> optionalMember = memberRepository.findByMail(email);

        if (optionalMember.isPresent()) {
            Member member = optionalMember.get();
            Cart cart = cartRepository.findByMemberId(member.getId());

            if (cart == null) { // 장바구니가 존재하지 않을 경우 생성
                cart = Cart.createCart(member);
                cartRepository.save(cart);
            }

            Item item = itemRepository.findBySerialNum(cartItemDto.getSerialNum()).orElseThrow(EntityNotFoundException::new);
            CartItem cartItem = cartItemRepository.findByCartIdAndItemSerialNum(cart.getId(), item.getSerialNum());

            if (cartItem == null) { // 장바구니에 상품이 존재하지 않을 경우
                cartItem = CartItem.createCartItem(cart, item, cartItemDto.getCount());
                cartItemRepository.save(cartItem);
            } else {
                cartItem.addCount(cartItemDto.getCount());
            }
            return cartItem.getId();

        } else {    // 회원 정보가 없는 경우 처리
            return null;
        }
    }

    // 장바구니 목록 조회
    public List<CartListDto> cartList(String email) {

        List<CartListDto> cartList = new ArrayList<>();

        Optional<Member> optionalMember = memberRepository.findByMail(email);

        if (optionalMember.isPresent()) {
            Member member = optionalMember.get();
            Cart cart = cartRepository.findByMemberId(member.getId());

            if (cart == null) {
                return cartList;
            }
            cartList = cartItemRepository.findCartListDto(cart.getId());

            return cartList;
        } else {
            return null;
        }
    }

    // 장바구니 상품 수량 변경
    public void updateCartItem(Long cartItemId, int count) {
        CartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow(EntityNotFoundException::new);
        log.info(cartItem.toString());
        cartItem.updateCount(count);
    }
}
