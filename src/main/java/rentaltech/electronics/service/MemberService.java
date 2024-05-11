package rentaltech.electronics.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rentaltech.electronics.entity.Member;
import rentaltech.electronics.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public String join(Member member) { // 회원가입
        memberRepository.save(member);
        return member.getId();
    }
}
