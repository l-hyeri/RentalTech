package rentaltech.electronics.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import rentaltech.electronics.entity.Rental;

import java.util.List;

public interface RentalRepository extends JpaRepository<Rental, Long> {

    // 해당 유저의 구매 이력을 조회 (주문 객체)
    @Query("select re from Rental re " +
            "where re.member.mail = :email " +
            "order by re.rentalDate desc")
    List<Rental> findRentals(@Param("email") String email);
}
