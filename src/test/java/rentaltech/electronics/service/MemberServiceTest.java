package rentaltech.electronics.service;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import rentaltech.electronics.entity.Member;
import rentaltech.electronics.repository.MemberRepository;

@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MemberService memberService;

    @Test
    public void 회원가입() throws Exception {

        // Given
        Member member = new Member();
        member.setId("honggildong");
        member.setPw("123456");
        member.setName("홍길동");
        member.setPhone("010-0000-0000");
        member.setAddress("서울특별시 00구 00동");
        member.setMail("hong@naver.com");

        // When
        String saveId = memberService.join(member);

        // Then

    }
}
