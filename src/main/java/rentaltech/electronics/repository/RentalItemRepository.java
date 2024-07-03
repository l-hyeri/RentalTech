package rentaltech.electronics.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rentaltech.electronics.entity.RentalItem;

public interface RentalItemRepository extends JpaRepository<RentalItem, Long> {

}
