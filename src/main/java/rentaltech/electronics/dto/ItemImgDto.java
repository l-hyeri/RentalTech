package rentaltech.electronics.dto;

import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import rentaltech.electronics.entity.Item;
import rentaltech.electronics.entity.ItemImg;

@Getter
@Setter
public class ItemImgDto {

    private String imgName; // 이미지 파일명

    private String originName;  // 원본 이미지 파일 이름

    private String imgUrl;  // 이미지 조회 경로

    private String repreImg;    // 대표 이미지 여부

    public static ItemImgDto toItemImgDto(ItemImg itemImg) {
        ItemImgDto itemImgDto = new ItemImgDto();
        itemImgDto.setImgName(itemImg.getImgName());
        itemImgDto.setOriginName(itemImg.getOriginName());
        itemImgDto.setImgUrl(itemImg.getImgUrl());
        itemImgDto.setRepreImg(itemImg.getRepreImg());

        return itemImgDto;
    }


}
