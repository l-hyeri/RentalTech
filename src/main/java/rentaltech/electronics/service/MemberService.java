package rentaltech.electronics.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rentaltech.electronics.dto.memberDto;
import rentaltech.electronics.entity.Member;
import rentaltech.electronics.repository.MemberRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void join(memberDto memberDto) { // 회원가입
        Member member = Member.toMember(memberDto);

        validateDuplicateMember(member);
        memberRepository.save(member);
    }

    // 중복회원 확인 (회원가입)
    public void validateDuplicateMember(Member member) {
        Optional<Member> findMember = memberRepository.findByMail(member.getMail());

        if (findMember.isPresent()) {
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }
}