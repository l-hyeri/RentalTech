package rentaltech.electronics.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;
import rentaltech.electronics.entity.ItemImg;
import rentaltech.electronics.repository.ItemImgRepository;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ItemImgService {

    @Value("${itemImgLocation}")
    private String itemImgLocation;

    private final FileService fileService;
    private final ItemImgRepository imgRepository;

    public void saveImg(ItemImg itemImg, MultipartFile itemImgFile) throws IOException {

        String originImgName = itemImgFile.getOriginalFilename();
        String imgName = "";
        String imgUrl = "";

        // 이미지 업로드
        if (!StringUtils.isEmpty(originImgName)) {
            imgName = fileService.uploadImg(itemImgLocation, originImgName, itemImgFile.getBytes());
            imgUrl = "/images/item/" + imgName;
        }

        itemImg.updateItemImg(originImgName, imgName, imgUrl);
        imgRepository.save(itemImg);
    }
}
