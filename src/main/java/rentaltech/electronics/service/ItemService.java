package rentaltech.electronics.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rentaltech.electronics.dto.ItemDto;
import rentaltech.electronics.entity.Item;
import rentaltech.electronics.repository.ItemRepository;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public void save(ItemDto itemDto) { // 상품 등록
        Item item = Item.toItem(itemDto);

        itemRepository.save(item);
    }
}
