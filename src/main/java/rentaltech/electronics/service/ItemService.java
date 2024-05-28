package rentaltech.electronics.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import rentaltech.electronics.dto.ItemDto;
import rentaltech.electronics.dto.ItemImgDto;
import rentaltech.electronics.entity.Item;
import rentaltech.electronics.entity.ItemImg;
import rentaltech.electronics.repository.ItemImgRepository;
import rentaltech.electronics.repository.ItemRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final ItemImgRepository itemImgRepository;
    private final ItemImgService itemImgService;

    public void save(ItemDto itemDto, List<MultipartFile> itemImgFileList) throws Exception { // 상품 등록

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
            itemImgService.saveImg(itemImg, itemImgFileList.get(i));
        }
    }

    public List<Item> findItemList() {  // 전체 상품 조회
        return itemRepository.findAll();
    }

    public ItemDto findItemDetails(Long serialNum) {    // 상품 상세 조회
        Item item = itemRepository.findBySerialNum(serialNum)
                .orElseThrow(() -> new IllegalArgumentException("해당 상품이 존재하지 않습니다."));

        ItemDto itemDto = ItemDto.toItemDto(item);

        List<ItemImg> itemImgList = itemImgRepository.findByItemSerialNum(serialNum);
        List<ItemImgDto> itemImgDtoList = itemImgList.stream()
                .map(ItemImgDto::toItemImgDto)
                .collect(Collectors.toList());

        itemDto.setItemImgDtoList(itemImgDtoList);
        return itemDto;
    }

    public void editItem(ItemDto itemDto, Long serialNum, List<MultipartFile> itemImgFileList) throws Exception { // 상품 정보 수정
        Item item = itemRepository.findBySerialNum(serialNum)
                .orElseThrow(() -> new IllegalArgumentException("해당 상품이 존재하지 않습니다."));

        item.setItem_name(itemDto.getItem_name());
        item.setPrice(itemDto.getPrice());
        item.setStock(itemDto.getStock());
        item.setStockStatus(itemDto.getStockStatus());
        item.setDetails(itemDto.getDetails());
        item.setPeriod(itemDto.getPeriod());
        itemRepository.save(item);


        // 이미지 파일 업데이트
        List<ItemImg> itemImgList = itemImgRepository.findByItemSerialNum(serialNum);

        for (int i = 0; i < itemImgFileList.size(); i++) {
            MultipartFile itemImgFile = itemImgFileList.get(i);

            if (i < itemImgList.size()) {
                // 기존 이미지를 수정
                itemImgService.editImg(itemImgList.get(i).getItemImgNum(), itemImgFile);
            } else {
                // 새로운 이미지를 추가
                ItemImg itemImg = new ItemImg();
                itemImg.setItem(item);
                if (i == 0) {
                    itemImg.setRepreImg("Y");
                } else {
                    itemImg.setRepreImg("N");
                }
                itemImgService.saveImg(itemImg, itemImgFile);
            }
        }

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
