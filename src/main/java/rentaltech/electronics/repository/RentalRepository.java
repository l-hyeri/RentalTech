package rentaltech.electronics.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rentaltech.electronics.entity.Rental;

public interface RentalRepository extends JpaRepository<Rental, Long> {
}
