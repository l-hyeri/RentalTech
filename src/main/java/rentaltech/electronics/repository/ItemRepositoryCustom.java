package rentaltech.electronics.repository;

import rentaltech.electronics.dto.ItemSearchDto;
import rentaltech.electronics.dto.MainItemDto;

import java.util.List;

public interface ItemRepositoryCustom {

    List<MainItemDto> getMainItemPage(ItemSearchDto itemSearchDto);

}
