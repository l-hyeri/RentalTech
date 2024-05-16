package rentaltech.electronics;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import rentaltech.electronics.entity.Member;

/**
 * 관리자 관련 정보를 미리 DB에 자동 저장하도록 구현
 */

@Component
@RequiredArgsConstructor
public class InitDB {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit1();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em;

        public void dbInit1() {
            Member member = createMember();
            em.persist(member);
        }

        private Member createMember() {
            Member member = new Member();
            member.setPw("1234");
            member.setName("manager");
            member.setPhone("010-0000-0000");
            member.setAddress("관리자 주소");
            member.setMail("manager@gmail.com");

            return member;
        }
    }

}
