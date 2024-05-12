package rentaltech.electronics.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rentaltech.electronics.entity.Member;
import rentaltech.electronics.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member join(Member member) { // 회원가입
        validateDuplicateMember(member);
        return memberRepository.save(member);
    }

    // 중복회원 확인
    public void validateDuplicateMember(Member member) {
        Member findMember = memberRepository.findByMail(member.getMail());

        if (findMember != null) {
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }
}
