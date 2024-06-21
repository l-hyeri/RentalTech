package rentaltech.electronics.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rentaltech.electronics.entity.Item;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long>,ItemRepositoryCustom {

    Optional<Item> findBySerialNum(Long serialNum);
}
