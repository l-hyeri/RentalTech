package rentaltech.electronics.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rentaltech.electronics.dto.ItemDto;
import rentaltech.electronics.entity.Item;
import rentaltech.electronics.repository.ItemRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public void save(ItemDto itemDto) { // 상품 등록
        Item item = Item.toItem(itemDto);

        validateDuplicateItem(item);
        itemRepository.save(item);
    }

    public void validateDuplicateItem(Item item) {  // 중복된 상품이 있는지 확인
        Optional<Item> findItem = itemRepository.findBySerialNum(item.getSerialNum()
        );

        if (findItem.isPresent()) {
            System.out.println("이미 등록된 상품");
            throw new IllegalStateException("이미 등록된 상품입니다.");
        }
    }
}
