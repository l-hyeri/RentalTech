package rentaltech.electronics.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rentaltech.electronics.entity.Member;

public interface MemberRepository extends JpaRepository<Member,String> {

    Member findByMail(String email);
}
