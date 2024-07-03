package rentaltech.electronics.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import rentaltech.electronics.dto.RentalDto;
import rentaltech.electronics.entity.Item;
import rentaltech.electronics.entity.Member;
import rentaltech.electronics.entity.Rental;
import rentaltech.electronics.entity.RentalItem;
import rentaltech.electronics.repository.ItemRepository;
import rentaltech.electronics.repository.MemberRepository;
import rentaltech.electronics.repository.RentalRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class RentalService {

    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;
    private final RentalRepository rentalRepository;

    // 상품 상세 조회에서 바로 렌탈
    public Long rental(RentalDto rentalDto, String email) {

        // Rental 객체 생성
        Optional<Member> optionalMember = memberRepository.findByMail(email);

        if (optionalMember.isPresent()) {
            // RentalItem(List) 객체 생성
            List<RentalItem> rentalItemList = new ArrayList<>();
            Item item = itemRepository.findBySerialNum(rentalDto.getSerialNum()).orElseThrow(EntityNotFoundException::new);
            rentalItemList.add(RentalItem.createRentalItem(item, rentalDto.getCount()));

            Member member = optionalMember.get();
            Rental rental = Rental.createRental(member, rentalItemList);

            // Rental 객체를 DB에 저장 (Cascade로 인해 OrderItem 객체도 함께 저장)
            rentalRepository.save(rental);
            return rental.getId();
        } else {
            return null;
        }
    }

    // 장바구니에서 렌탈
    public Long rentals(List<RentalDto> rentalDtoList, String email) {

        // 로그인한 사용자 조회
        Optional<Member> optionalMember = memberRepository.findByMail(email);

        if (optionalMember.isPresent()) {
            Member member = optionalMember.get();

            // rentalDto 객체를 이용하여 item 객체와 count 값을 얻어낸 뒤, 이를 이용하여 RentalItem 객체(들) 생성
            List<RentalItem> rentalItemList = new ArrayList<>();
            for (RentalDto rentalDto : rentalDtoList) {
                Item item = itemRepository.findBySerialNum(rentalDto.getSerialNum()).orElseThrow(EntityNotFoundException::new);
                RentalItem rentalItem = RentalItem.createRentalItem(item, rentalDto.getCount());
                rentalItemList.add(rentalItem);
            }

            // Rental Entity 클래스에 존재하는 createRental 메소드로 Rental 생성 및 저장
            Rental rental = Rental.createRental(member, rentalItemList);
            rentalRepository.save(rental);
            return rental.getId();
        } else {
            return null;
        }
    }
}
