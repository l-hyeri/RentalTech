package rentaltech.electronics.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ItemImg {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long itemImgNum;

    private String imgName; // 이미지 파일명

    private String originName;  // 원본 이미지 파일 이름

    private String imgUrl;  // 이미지 조회 경로
        
    private String repreImg;    // 대표 이미지 여부

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="serialNum")
    private Item item;
}
