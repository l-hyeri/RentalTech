package rentaltech.electronics.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
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
public class RentalService {

    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;
    private final RentalRepository rentalRepository;

    // 상품 상세 조회에서 바로 렌탈
    public Long rental(RentalDto rentalDto, String email) {

        // RentalItem(List) 객체 생성
        List<RentalItem> rentalItemList = new ArrayList<>();
        Item item = itemRepository.findById(rentalDto.getItemId()).orElseThrow(EntityNotFoundException::new);
        rentalItemList.add(RentalItem.createRentalItem(item, rentalDto.getCount()));

        // Rental 객체 생성
        Optional<Member> optionalMember= memberRepository.findByMail(email);

        if (optionalMember.isPresent()) {
            Member member = optionalMember.get();
            Rental rental = Rental.createRental(member, rentalItemList);

            // Rental 객체를 DB에 저장 (Cascade로 인해 OrderItem 객체도 함께 저장)
            rentalRepository.save(rental);
            return rental.getId();
        } else {
            return null;
        }
    }
}
