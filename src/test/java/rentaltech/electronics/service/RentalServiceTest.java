package rentaltech.electronics.service;


import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import rentaltech.electronics.constant.ItemStockStatus;
import rentaltech.electronics.constant.RentalStatus;
import rentaltech.electronics.dto.RentalDto;
import rentaltech.electronics.entity.Item;
import rentaltech.electronics.entity.Member;
import rentaltech.electronics.entity.Rental;
import rentaltech.electronics.entity.RentalItem;
import rentaltech.electronics.repository.ItemRepository;
import rentaltech.electronics.repository.MemberRepository;
import rentaltech.electronics.repository.RentalRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
public class RentalServiceTest {

    @Autowired
    private RentalService rentalService;

    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    MemberRepository memberRepository;

    public Item saveItem() {
        Item item = new Item();
        item.setSerialNum(1L);
        item.setItem_name("테스트 상품");
        item.setPeriod(100000);
        item.setStockStatus(ItemStockStatus.SELL);
        item.setStock(100);
        item.setDetails("테스트 상품 상세 설명");
        item.setPeriod(1);

        return itemRepository.save(item);
    }

    public Member saveMember() {
        Member member = new Member();
        member.setMail("test@test.com");

        return memberRepository.save(member);
    }

    @Test
    @DisplayName("렌탈 테스트")
    public void rental() {

        Item item = saveItem();
        Member member = saveMember();

        // 상품 상세 페이지 화면에서 넘어오는 값들을 설정
        RentalDto rentalDto = new RentalDto();
        rentalDto.setSerialNum(item.getSerialNum());
        rentalDto.setCount(10);

        // 렌탈 객체 DB에 저장
        Long rentalId = rentalService.rental(rentalDto, member.getMail());

        // 저장된 렌탈 객체 조회
        Rental rental = rentalRepository.findById(rentalId).orElseThrow(EntityNotFoundException::new);

        // 1. DB에 저장된 렌탈 객체에서 렌탈 상품 추출 (1개)
        List<RentalItem> rentalItemList = rental.getRentalItemList();

        // 2. 위에서 만든 렌탈 상품 총 가격 (1개)
        int totalPrice = rentalDto.getCount() * item.getPrice();

        // 3. 1의 가격과 2의 가격이 같은지 테스트
        assertEquals(totalPrice, rental.getTotalPrice());
    }

    @Test
    @DisplayName("렌탈 취소 테스트")
    public void cancelRental() {
        Item item = saveItem();
        Member member = saveMember();

        RentalDto rentalDto = new RentalDto();
        rentalDto.setCount(10);
        rentalDto.setSerialNum(item.getSerialNum());

        // 렌탈 객체 저장
        Long rentalId = rentalService.rental(rentalDto, member.getMail());

        // 렌탈된 객체를 조회한 뒤에 렌탈 취소
        Rental rental = rentalRepository.findById(rentalId).orElseThrow(EntityNotFoundException::new);
        rentalService.rentalCancel(rentalId);

        // 렌탈의 상태가 "CANCEL"이고 처음 수량 100이 맞다면 테스트 통과
        assertEquals(RentalStatus.CANCEL, rental.getRentalStatus());
        assertEquals(100, item.getStock());
    }
}
