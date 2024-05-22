package rentaltech.electronics.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rentaltech.electronics.entity.ItemImg;

public interface ItemImgRepository extends JpaRepository<ItemImg, Long> {
}
