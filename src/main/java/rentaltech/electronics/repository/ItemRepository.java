package rentaltech.electronics.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rentaltech.electronics.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
