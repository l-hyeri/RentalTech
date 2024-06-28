package rentaltech.electronics.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import rentaltech.electronics.dto.CartListDto;
import rentaltech.electronics.entity.CartItem;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    CartItem findByCartIdAndItemSerialNum(Long cartId, Long serialNum);

    @Query("select new rentaltech.electronics.dto.CartListDto(ci.id, i.item_name, i.price, ci.count, im.imgUrl) " +
            "from CartItem ci, ItemImg im " +
            "join ci.item i " +
            "where ci.cart.id = :cartId " +
            "and im.item.serialNum = ci.item.serialNum " +
            "and im.repreImg = 'Y' ")
    List<CartListDto> findCartListDto(Long cartId);
}
