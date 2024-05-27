package rentaltech.electronics.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import rentaltech.electronics.dto.ItemDto;
import rentaltech.electronics.entity.Item;
import rentaltech.electronics.entity.ItemImg;
import rentaltech.electronics.repository.ItemImgRepository;
import rentaltech.electronics.repository.ItemRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final ItemImgRepository itemImgRepository;
    private final ItemImgService itemImgService;

    public void save(ItemDto itemDto, List<MultipartFile> itemImgFileList)throws Exception { // 상품 등록

        // 상품 정보 등록 (1)
        Item item = Item.toItem(itemDto);

        validateDuplicateItem(item);
        itemRepository.save(item);

        // 이미지 파일 등록 (2)
        for (int i = 0; i < itemImgFileList.size(); i++) {
            ItemImg itemImg = new ItemImg();
            itemImg.setItem(item);

            if (i == 0) {   // 첫번째에 위치한 이미지 파일을 항상 대표 사진으로 지정
                itemImg.setRepreImg("Y");
            } else {
                itemImg.setRepreImg("N");
            }
            itemImgService.saveImg(itemImg,itemImgFileList.get(i));
        }
    }

    public List<Item> findItemList() {  // 전체 상품 조회
        return itemRepository.findAll();
    }

    public ItemDto findItemDetails(Long serialNum) {
        Item item = itemRepository.findBySerialNum(serialNum)
                .orElseThrow(() -> new IllegalArgumentException("상품없음"));
        return ItemDto.toItemDto(item);
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
