package rentaltech.electronics.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rentaltech.electronics.entity.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {

    Optional<Member> findByMail(String email);
}
