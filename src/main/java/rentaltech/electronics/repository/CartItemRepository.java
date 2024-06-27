package rentaltech.electronics.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rentaltech.electronics.entity.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    CartItem findByCartIdAndItemSerialNum(Long cartId, Long serialNum);
}
